package tests;

import data.AuthorGenerator;
import data.ProjectGenerator;
import data.SessionGenerator;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import models.database.AuthorDao;
import models.database.ProjectDao;
import models.database.SessionDao;
import org.testng.annotations.BeforeTest;
import steps.database.AuthorSteps;
import steps.database.ProjectSteps;
import steps.database.SessionSteps;

public abstract class BaseTest {

    private final SessionDao session = SessionGenerator.generateSession();
    private final AuthorDao author = AuthorGenerator.generateAuthor();
    private final ProjectDao project = ProjectGenerator.generateProject();
    private final SessionSteps sessionSteps = new SessionSteps();
    private final AuthorSteps authorSteps = new AuthorSteps();
    private final ProjectSteps projectSteps = new ProjectSteps();
    protected static Long sessionId;
    protected static Long authorId;
    protected static Long projectId;

    @BeforeTest
    public void setup() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        sessionId = sessionSteps.insertSession(session);
        authorId = authorSteps.insertAuthorIfAbsent(author);
        projectId = projectSteps.insertProjectIfAbsent(project);
    }
}
