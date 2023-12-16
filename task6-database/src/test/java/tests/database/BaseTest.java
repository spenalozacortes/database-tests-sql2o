package tests.database;

import models.database.TestDAO;
import org.testng.annotations.BeforeTest;
import steps.database.TestSteps;

import java.util.ArrayList;
import java.util.List;

public class BaseTest {

    private final TestSteps testSteps = new TestSteps();
    protected final List<Long> newIds = new ArrayList<>();

    @BeforeTest
    public void setup() {
        // Get list of tests from database
        List<TestDAO> tests = testSteps.getTests("55", 10);
        for (TestDAO test : tests) {
            // Set current author and project
            test.setAuthor_id(1L);
            test.setProject_id(4L);
            newIds.add(testSteps.addTest(test));
        }
    }
}
