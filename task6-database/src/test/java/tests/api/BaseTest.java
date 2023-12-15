package tests.api;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import models.database.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import steps.database.TestSteps;

public abstract class BaseTest {

    private final TestSteps testSteps = new TestSteps();

    @BeforeTest
    public void setup() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @AfterMethod
    public void afterTest() {
        Test test = new Test();
        test.setProject_id(6L);
        test.setSession_id(3L);
        testSteps.addTest(test);
    }
}
