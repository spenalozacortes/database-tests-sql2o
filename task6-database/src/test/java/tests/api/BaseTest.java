package tests.api;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import models.database.TestDAO;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import steps.database.TestSteps;
import utils.SessionUtils;
import utils.TestUtils;

import java.time.LocalDateTime;

public abstract class BaseTest {

    private static final Long AUTHOR_ID = SessionUtils.getAuthorId();
    private static final Long SESSION_ID = SessionUtils.getSessionId();
    private static final Long PROJECT_ID = SessionUtils.getProjectId();
    private static final String ENV = System.getenv("COMPUTERNAME");
    private final TestSteps testSteps = new TestSteps();
    private TestDAO test;
    private TestDAO testFromDb;
    private Long testId;

    @BeforeSuite
    public void setup() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
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
        test.setSession_id(SESSION_ID);
        test.setStart_time(startTime);
        test.setEnd_time(endTime);
        test.setEnv(ENV);
        test.setAuthor_id(AUTHOR_ID);
        testId = testSteps.addTest(test);
        test.setId(testId);

        // Assert test was added to database
        testFromDb = testSteps.getTestById(testId);
        Assert.assertEquals(test, testFromDb, "Test added to database is not correct");
    }
}
