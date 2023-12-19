package utils;

import config.TestDataConfig;
import lombok.experimental.UtilityClass;
import models.database.AuthorDao;
import models.database.ProjectDao;
import models.database.SessionDao;
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
    private final SessionSteps sessionSteps = new SessionSteps();
    private final AuthorSteps authorSteps = new AuthorSteps();
    private final ProjectSteps projectSteps = new ProjectSteps();
    private final SessionDao session = new SessionDao();
    private AuthorDao author;
    private ProjectDao project;

    public static Long getSessionId() {
        // Create and add session to database
        session.setSessionKey(RandomUtils.generateSessionId());
        session.setBuildNumber(BUILD_NUMBER);
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
