package tests.api;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import models.database.Session;
import models.database.Test;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import steps.database.SessionSteps;
import steps.database.TestSteps;
import utils.RandomUtils;
import utils.TestUtils;

import java.time.LocalDateTime;

public abstract class BaseTest {

    private final Session session = new Session();
    private final SessionSteps sessionSteps = new SessionSteps();
    private final Test test = new Test();
    private final TestSteps testSteps = new TestSteps();
    private Long sessionId;

    @BeforeTest
    public void setup() {
//        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        session.setSession_key(RandomUtils.generateRandomNumber(13));
        session.setBuild_number(7L);
        sessionId = sessionSteps.addSession(session);
    }

    @AfterMethod
    public void afterTest(ITestResult result) {
        int status = result.getStatus();
        String name = result.getMethod().getMethodName();
        String className = result.getTestClass().getName();
        String methodName = String.format("%s.%s", TestUtils.getPackageName(className), name);
        Long projectId = 6L;
        LocalDateTime startTime = LocalDateTime.now();
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
        test.setEnv(env);

        testSteps.addTest(test);
    }
}
