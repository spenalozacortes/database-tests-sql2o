package tests.database;

import models.database.AuthorDAO;
import models.database.TestDAO;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import steps.database.AuthorSteps;
import steps.database.TestSteps;
import utils.RandomUtils;

import java.util.ArrayList;
import java.util.List;

public class BaseTest {

    private static final int DIGIT_RANGE = 10;
    private static final int MAX_TESTS = 10;
    private static final Long PROJECT_ID = 6L;
    private static final String LOGIN = "smpcsdfsdf";
    private final AuthorSteps authorSteps = new AuthorSteps();
    private final TestSteps testSteps = new TestSteps();
    private AuthorDAO author;
    private Long authorId;
    protected final List<Long> newIds = new ArrayList<>();

    @BeforeTest
    public void setup() {
        // Get current author
        author = authorSteps.getAuthorByLogin(LOGIN);
        authorId = author.getId();

        // Get list of tests from database
        int randomDigit = RandomUtils.getRandomInt(DIGIT_RANGE);
        List<TestDAO> tests = testSteps.getTests(String.format("%d%d", randomDigit, randomDigit), MAX_TESTS);
        for (TestDAO test : tests) {
            // Set current author and project
            test.setAuthor_id(authorId);
            test.setProject_id(PROJECT_ID);
            newIds.add(testSteps.addTest(test));
        }
    }

    @AfterTest
    public void cleanup() {
        // Delete copied tests from database
        for (Long id : newIds) {
            testSteps.deleteTest(id);
        }
    }
}
