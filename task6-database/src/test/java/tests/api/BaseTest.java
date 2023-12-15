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

public abstract class BaseTest {

    private final Session session = new Session();
    private final SessionSteps sessionSteps = new SessionSteps();
    private final TestSteps testSteps = new TestSteps();

    @BeforeTest
    public void setup() {
//        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        session.setSession_key(RandomUtils.generateRandomNumber(13));
        session.setBuild_number(7L);
        sessionSteps.addSession(session);
    }

    @AfterMethod
    public void afterTest(ITestResult result) {

    }
}
