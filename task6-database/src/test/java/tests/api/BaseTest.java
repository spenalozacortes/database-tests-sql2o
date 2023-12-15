package tests.api;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import models.database.Author;
import models.database.Session;
import models.database.Test;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import steps.database.AuthorSteps;
import steps.database.SessionSteps;
import steps.database.TestSteps;
import utils.RandomUtils;
import utils.TestUtils;

import java.time.LocalDateTime;

public abstract class BaseTest {

    private final Session session = new Session();
    private final SessionSteps sessionSteps = new SessionSteps();
    private final AuthorSteps authorSteps = new AuthorSteps();
    private final Test test = new Test();
    private final TestSteps testSteps = new TestSteps();
    private Long sessionId;
    private Author author;
    private Long authorId;

    @BeforeTest
    public void setup() {
//        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        session.setSession_key(RandomUtils.generateRandomNumber(13));
        session.setBuild_number(7L);
        sessionId = sessionSteps.addSession(session);

        String name = "Stephanie";
        String login = "gjlhkj";
        String email = "smpc@mail.com";
        author = authorSteps.getAuthorByLogin(login);
        if (author.getId() == null) {
            author.setName(name);
            author.setLogin(login);
            author.setEmail(email);
            authorId = authorSteps.addAuthor(author);
        } else {
            authorId = author.getId();
        }
    }

    @AfterMethod
    public void afterTest(ITestResult result) {
        int status = result.getStatus();
        String name = result.getMethod().getMethodName();
        String className = result.getTestClass().getName();
        String methodName = String.format("%s.%s", TestUtils.getPackageName(className), name);
        Long projectId = 6L;
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = startTime.plus(TestUtils.getTestDuration(result));
        String env = System.getenv("COMPUTERNAME");

        test.setName(name);
        test.setMethod_name(methodName);
        if (status == 1) {
            test.setStatus_id(1);
        } else if (status == 2) {
            test.setStatus_id(2);
        } else {
            test.setStatus_id(3);
        }
        test.setProject_id(projectId);
        test.setSession_id(sessionId);
        test.setStart_time(startTime);
        test.setEnd_time(endTime);
        test.setEnv(env);
        test.setAuthor_id(authorId);
        testSteps.addTest(test);
    }
}
