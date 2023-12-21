package tests;

import config.TestDataConfig;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import models.database.SessionDao;
import org.testng.annotations.BeforeTest;
import steps.database.AuthorSteps;
import steps.database.ProjectSteps;
import steps.database.SessionSteps;
import utils.RandomUtils;

public abstract class BaseTest {

    private static final Long BUILD_NUMBER = TestDataConfig.getBuildNumber();
    private static final String AUTHOR_NAME = TestDataConfig.getAuthorName();
    private static final String LOGIN = TestDataConfig.getAuthorLogin();
    private static final String EMAIL = TestDataConfig.getAuthorEmail();
    private static final String PROJECT_NAME = TestDataConfig.getProjectName();
    private final SessionDao session = new SessionDao();
    private final SessionSteps sessionSteps = new SessionSteps();
    private final AuthorSteps authorSteps = new AuthorSteps();
    private final ProjectSteps projectSteps = new ProjectSteps();
    protected static Long sessionId;
    protected static Long authorId;
    protected static Long projectId;

    @BeforeTest
    public void setup() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        session.setSessionKey(RandomUtils.generateSessionId());
        session.setBuildNumber(BUILD_NUMBER);
        sessionId = sessionSteps.insertSession(session);

        authorId = authorSteps.getAuthorId(LOGIN, AUTHOR_NAME, EMAIL);

        projectId = projectSteps.getProjectId(PROJECT_NAME);
    }
}
