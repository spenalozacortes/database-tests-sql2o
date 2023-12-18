package utils;

import config.TestDataConfig;
import lombok.experimental.UtilityClass;
import models.database.AuthorDAO;
import models.database.ProjectDAO;
import models.database.SessionDAO;
import steps.database.AuthorSteps;
import steps.database.ProjectSteps;
import steps.database.SessionSteps;

@UtilityClass
public class SessionUtils {

    private static final String LOGIN = TestDataConfig.getAuthorLogin();
    private static final String AUTHOR_NAME = TestDataConfig.getAuthorName();
    private static final String EMAIL = TestDataConfig.getAuthorEmail();
    private static final String PROJECT_NAME = TestDataConfig.getProjectName();
    private static final Long BUILD_NUMBER = TestDataConfig.getBuildNumber();
    private static final int SESSION_KEY_LENGTH = 13;
    private final SessionSteps sessionSteps = new SessionSteps();
    private final AuthorSteps authorSteps = new AuthorSteps();
    private final ProjectSteps projectSteps = new ProjectSteps();
    private final SessionDAO session = new SessionDAO();
    private AuthorDAO author;
    private ProjectDAO project;

    public static Long getSessionId() {
        // Create and add session to database
        session.setSession_key(RandomUtils.generateRandomNumber(SESSION_KEY_LENGTH));
        session.setBuild_number(BUILD_NUMBER);
        return sessionSteps.addSession(session);
    }

    public static Long getAuthorId() {
        author = authorSteps.getAuthorByLogin(LOGIN);
        // If author exists, get author id, else create and add author to database
        if (author.getId() == null) {
            author.setName(AUTHOR_NAME);
            author.setLogin(LOGIN);
            author.setEmail(EMAIL);
            return authorSteps.addAuthor(author);
        } else {
            return author.getId();
        }
    }

    public static Long getProjectId() {
        project = projectSteps.getProjectByName(PROJECT_NAME);
        // If project exists, get project id, else create and add project to database
        if (project.getId() == null) {
            project.setName(PROJECT_NAME);
            return projectSteps.addProject(project);
        } else {
            return project.getId();
        }
    }
}
