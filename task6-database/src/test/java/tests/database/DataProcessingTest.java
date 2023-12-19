package tests.database;

import models.database.TestDao;
import org.testng.Assert;
import org.testng.annotations.Test;
import steps.database.TestSteps;
import utils.RandomUtils;

public class DataProcessingTest extends BaseTest {

    private final TestSteps testSteps = new TestSteps();
    private TestDao test;

    @Test
    public void simulateTests() {
        for(Long id : newIds) {
            test = testSteps.getTestById(id);
            int newStatusId = RandomUtils.getRandomStatus();
            test.setStatusId(newStatusId);
            testSteps.updateTest(test);
            Assert.assertEquals(newStatusId, testSteps.getTestById(id).getStatusId(), "Information was not updated");
        }
    }
}
