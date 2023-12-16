package tests.database;

import models.database.TestDAO;
import org.testng.annotations.Test;
import steps.database.TestSteps;
import utils.TestUtils;

public class DataProcessingTest extends BaseTest {

    private final TestSteps testSteps = new TestSteps();
    private TestDAO test = new TestDAO();

    @Test
    public void simulateTests() {
        for(Long id : newIds) {
            test = testSteps.getTestById(id);
            int initialStatusId = test.getStatus_id();
            int newStatusId = TestUtils.getNewStatusId(initialStatusId);
            test.setStatus_id(newStatusId);
            testSteps.updateTest(test);
        }
    }
}
