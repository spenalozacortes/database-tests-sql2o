package tests.database;

import steps.database.TestSteps;
import models.database.TestModel;
import org.testng.annotations.Test;

import java.util.List;

public class DataProcessingTest {

    private final TestSteps testSteps = new TestSteps();

    @Test
    public void test() {
        List<TestModel> tests = testSteps.getTests(88, 10);
        for(TestModel test : tests) {
            System.out.println(test);
        }
    }
}
