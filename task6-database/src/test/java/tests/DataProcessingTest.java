package tests;

import models.database.TestDao;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import steps.database.TestSteps;
import utils.DbConnector;
import utils.RandomUtils;

import java.util.ArrayList;
import java.util.List;

public class DataProcessingTest extends BaseTest {

    private static final int DIGIT_RANGE = 10;
    private static final int MAX_TESTS = 10;
    private final TestSteps testSteps = new TestSteps();
    private final List<Long> newIds = new ArrayList<>();

    @BeforeMethod
    public void beforeTest() {
        // Get list of tests from database
        int randomDigit = RandomUtils.getRandomInt(DIGIT_RANGE);
        List<TestDao> tests = testSteps.getTests(String.format("%d%d", randomDigit, randomDigit), MAX_TESTS);
        for (TestDao test : tests) {
            // Set current author and project
            test.setAuthorId(AUTHOR.getId());
            test.setProjectId(PROJECT.getId());
            newIds.add(testSteps.insertTest(test));
        }
    }

    @Test
    public void simulateTests() {
        for (Long id : newIds) {
            TestDao test = testSteps.getTestById(id);
            int newStatusId = RandomUtils.getRandomStatus();
            test.setStatusId(newStatusId);
            testSteps.updateTest(test);
            Assert.assertEquals(newStatusId, testSteps.getTestById(id).getStatusId(), "Information was not updated");
        }
    }

    @AfterMethod
    public void cleanup() {
        // Delete copied tests from database
        for (Long id : newIds) {
            testSteps.deleteTest(id);
            Assert.assertNull(testSteps.getTestById(id).getId(), "Test was not deleted");
        }
        DbConnector.closeConnection();
    }
}
