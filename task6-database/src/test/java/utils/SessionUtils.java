package utils;

import config.DataReader;
import lombok.experimental.UtilityClass;
import models.database.AuthorDAO;
import steps.database.AuthorSteps;

@UtilityClass
public class SessionUtils {

    private static final String LOGIN = DataReader.getAuthorLogin();
    private static final String NAME = DataReader.getAuthorName();
    private static final String EMAIL = DataReader.getAuthorEmail();
    private final AuthorSteps authorSteps = new AuthorSteps();
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
}
