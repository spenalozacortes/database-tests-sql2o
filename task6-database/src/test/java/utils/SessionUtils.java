package utils;

import config.DataReader;
import lombok.experimental.UtilityClass;
import models.database.AuthorDAO;
import models.database.SessionDAO;
import steps.database.AuthorSteps;
import steps.database.SessionSteps;

@UtilityClass
public class SessionUtils {

    private static final String LOGIN = DataReader.getAuthorLogin();
    private static final String NAME = DataReader.getAuthorName();
    private static final String EMAIL = DataReader.getAuthorEmail();
    private static final Long BUILD_NUMBER = DataReader.getBuildNumber();
    private static final int SESSION_KEY_LENGTH = 13;
    private final SessionSteps sessionSteps = new SessionSteps();
    private final AuthorSteps authorSteps = new AuthorSteps();
    private final SessionDAO session = new SessionDAO();
    private AuthorDAO author;

    public static Long getAuthorId() {
        author = authorSteps.getAuthorByLogin(LOGIN);
        // If author exists, get author id, else create and add author to database
        if (author.getId() == null) {
            author.setName(NAME);
            author.setLogin(LOGIN);
            author.setEmail(EMAIL);
            return authorSteps.addAuthor(author);
        } else {
            return author.getId();
        }
    }

    public static Long getSessionId() {
        // Create and add session to database
        session.setSession_key(RandomUtils.generateRandomNumber(SESSION_KEY_LENGTH));
        session.setBuild_number(BUILD_NUMBER);
        return sessionSteps.addSession(session);
    }
}
