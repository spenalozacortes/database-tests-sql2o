package tests.database;

import models.database.TestModel;
import org.testng.annotations.Test;
import steps.database.TestSteps;

import java.util.List;

public class DataProcessingTest extends BaseTest {

    private TestSteps testSteps = new TestSteps();

    @Test
    public void test() {
        /*List<TestModel> tests = testSteps.getTests(88, 10);
        for(TestModel test : tests) {
            System.out.println(test);
        }*/
    }
}
