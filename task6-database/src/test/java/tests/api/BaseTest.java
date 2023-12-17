package tests.api;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import models.database.AuthorDAO;
import models.database.SessionDAO;
import models.database.TestDAO;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import steps.database.AuthorSteps;
import steps.database.SessionSteps;
import steps.database.TestSteps;
import utils.RandomUtils;
import utils.TestUtils;

import java.time.LocalDateTime;

public abstract class BaseTest {

    private static final int SESSION_KEY_LENGTH = 13;
    private static final Long BUILD_NUMBER = 7L;
    private static final String NAME = "Stephanie";
    private static final String LOGIN = "smpc";
    private static final String EMAIL = "smpc@mail.com";
    private static final Long PROJECT_ID = 6L;
    private static final String ENV = System.getenv("COMPUTERNAME");
    private final SessionDAO session = new SessionDAO();
    private final SessionSteps sessionSteps = new SessionSteps();
    private final AuthorSteps authorSteps = new AuthorSteps();
    private final TestSteps testSteps = new TestSteps();
    private AuthorDAO author;
    private TestDAO test;
    private TestDAO testFromDb;
    private Long sessionId;
    private Long authorId;
    private Long testId;

    @BeforeSuite
    public void setup() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        // Create and add session to database
        session.setSession_key(RandomUtils.generateRandomNumber(SESSION_KEY_LENGTH));
        session.setBuild_number(BUILD_NUMBER);
        sessionId = sessionSteps.addSession(session);

        // If author exists, get author id, else create and add author to database
        author = authorSteps.getAuthorByLogin(LOGIN);
        if (author.getId() == null) {
            author.setName(NAME);
            author.setLogin(LOGIN);
            author.setEmail(EMAIL);
            authorId = authorSteps.addAuthor(author);
        } else {
            authorId = author.getId();
        }
    }

    @AfterMethod
    public void afterTest(ITestResult result) {
        // Get info from current test
        int status = result.getStatus();
        String name = result.getMethod().getMethodName();
        String className = result.getTestClass().getName();
        String methodName = String.format("%s.%s", TestUtils.getPackageName(className), name);
        LocalDateTime startTime = LocalDateTime.now().withNano(0);
        LocalDateTime endTime = startTime.plus(TestUtils.getTestDuration(result)).withNano(0);

        // Create and add test to database
        test = new TestDAO();
        test.setName(name);
        test.setMethod_name(methodName);
        if (status == 1) {
            test.setStatus_id(1);
        } else if (status == 2) {
            test.setStatus_id(2);
        } else {
            test.setStatus_id(3);
        }
        test.setProject_id(PROJECT_ID);
        test.setSession_id(sessionId);
        test.setStart_time(startTime);
        test.setEnd_time(endTime);
        test.setEnv(ENV);
        test.setAuthor_id(authorId);
        testId = testSteps.addTest(test);
        test.setId(testId);

        // Assert test was added to database
        testFromDb = testSteps.getTestById(testId);
        Assert.assertEquals(test, testFromDb, "Test added to database is not correct");
    }
}
