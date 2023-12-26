package tests;

import config.EnvironmentConfig;
import constants.ApiResponsesPaths;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.api.PostResponse;
import models.api.UserResponse;
import models.database.TestDao;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import steps.api.PostsSteps;
import steps.api.UsersSteps;
import steps.database.TestSteps;
import utils.JsonMapperUtils;
import utils.RandomUtils;
import utils.ResponseUtils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ApiTests extends BaseTest {

    private static final int POST_ID = 99;
    private static final int POST_ID_NOT_FOUND = 150;
    private static final int USER_ID_GET = 10;
    private static final int USER_ID_POST = 1;
    private static final int USER_ID = 5;
    private static final int TITLE_LENGTH = 10;
    private static final int BODY_LENGTH = 50;
    private final PostsSteps postsSteps = new PostsSteps();
    private final UsersSteps usersSteps = new UsersSteps();
    private final TestSteps testSteps = new TestSteps();

    @Test
    public void getPosts() {
        Response response = postsSteps.getPosts();
        Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK, "Status code is not 200");
        ResponseUtils.checkContentType(response, ContentType.JSON);
        Assert.assertTrue(postsSteps.arePostsSorted(response), "Posts are not sorted in ascending order by id");
    }

    @Test
    public void foundPost() {
        Response response = postsSteps.getPostById(POST_ID);
        Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK, "Status code is not 200");
        PostResponse post = response.as(PostResponse.class);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(post.getUserId(), USER_ID_GET, "userId is incorrect");
        softAssert.assertEquals(post.getId(), POST_ID, "id is incorrect");
        softAssert.assertFalse(post.getTitle().isEmpty(), "title is empty");
        softAssert.assertFalse(post.getBody().isEmpty(), "body is empty");
        softAssert.assertAll();
    }

    @Test
    public void notFoundPost() {
        Response response = postsSteps.getPostById(POST_ID_NOT_FOUND);
        Assert.assertEquals(response.statusCode(), HttpStatus.SC_NOT_FOUND, "Status code is not 404");
        ResponseUtils.isBodyEmpty(response);
    }

    @Test
    public void sendPost() {
        String randomTitle = RandomUtils.generateRandomString(TITLE_LENGTH);
        String randomBody = RandomUtils.generateRandomString(BODY_LENGTH);
        PostResponse post = new PostResponse();
        post.setTitle(randomTitle);
        post.setBody(randomBody);
        post.setUserId(USER_ID_POST);
        Response response = postsSteps.sendPost(post);
        Assert.assertEquals(response.statusCode(), HttpStatus.SC_CREATED, "Status code is not 201");
        PostResponse actualPost = response.as(PostResponse.class);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualPost.getUserId(), USER_ID_POST, "userId is incorrect");
        softAssert.assertEquals(actualPost.getTitle(), randomTitle, "title is incorrect");
        softAssert.assertEquals(actualPost.getBody(), randomBody, "body is incorrect");
        softAssert.assertFalse(String.valueOf(actualPost.getId()).isEmpty(), "id is not present");
        softAssert.assertAll();
    }

    @Test
    public void getUsers() {
        Response response = usersSteps.getUsers();
        Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK, "Status code is not 200");
        ResponseUtils.checkContentType(response, ContentType.JSON);
        UserResponse actualUser = UsersSteps.getUserFromListById(response, USER_ID);
        UserResponse expectedUser = JsonMapperUtils.deserialize(ApiResponsesPaths.USER_PATH, UserResponse.class);
        Assert.assertEquals(actualUser, expectedUser, "User data is not as expected");
    }

    @Test
    public void foundUser() {
        Response response = usersSteps.getUserById(USER_ID);
        Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK, "Status code is not 200");
        UserResponse actualUser = response.as(UserResponse.class);
        UserResponse expectedUser = JsonMapperUtils.deserialize(ApiResponsesPaths.USER_PATH, UserResponse.class);
        Assert.assertEquals(actualUser, expectedUser, "User data is not as expected");
    }

    @AfterMethod
    public void afterTest(ITestResult result) {
        LocalDateTime startTime = new Timestamp(result.getStartMillis()).toLocalDateTime().truncatedTo(ChronoUnit.SECONDS);
        LocalDateTime endTime = new Timestamp(result.getEndMillis()).toLocalDateTime().truncatedTo(ChronoUnit.SECONDS);

        // Create and add test to database
        TestDao test = TestDao.builder()
                .name(result.getMethod().getMethodName())
                .statusId(result.getStatus())
                .methodName(result.getInstanceName())
                .projectId(PROJECT.getId())
                .sessionId(SESSION.getId())
                .startTime(startTime.toString())
                .endTime(endTime.toString())
                .env(EnvironmentConfig.getEnv())
                .browser(EnvironmentConfig.getBrowser())
                .authorId(AUTHOR.getId())
                .build();
        Long testId = testSteps.insertTest(test);
        test.setId(testId);

        // Assert test was added to database
        TestDao testFromDb = testSteps.getTestById(testId);
        Assert.assertEquals(testFromDb, test, "Test added to database is not correct");
    }
}
