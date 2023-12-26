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

    protected static final SessionDao SESSION = SessionGenerator.generateSession();
    protected static final AuthorDao AUTHOR = AuthorGenerator.generateAuthor();
    protected static final ProjectDao PROJECT = ProjectGenerator.generateProject();
    private final SessionSteps sessionSteps = new SessionSteps();
    private final AuthorSteps authorSteps = new AuthorSteps();
    private final ProjectSteps projectSteps = new ProjectSteps();

    @BeforeTest
    public void setup() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        SESSION.setId(sessionSteps.insertSession(SESSION));
        AUTHOR.setId(authorSteps.insertAuthorIfAbsent(AUTHOR));
        PROJECT.setId(projectSteps.insertProjectIfAbsent(PROJECT));
    }
}
