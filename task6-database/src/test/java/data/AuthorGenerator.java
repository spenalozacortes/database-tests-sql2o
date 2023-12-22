package data;

import config.TestDataConfig;
import lombok.experimental.UtilityClass;
import models.database.AuthorDao;

@UtilityClass
public class AuthorGenerator {

    private static final String AUTHOR_NAME = TestDataConfig.getAuthorName();
    private static final String LOGIN = TestDataConfig.getAuthorLogin();
    private static final String EMAIL = TestDataConfig.getAuthorEmail();

    public static AuthorDao generateAuthor() {
        AuthorDao author = new AuthorDao();
        author.setName(AUTHOR_NAME);
        author.setLogin(LOGIN);
        author.setEmail(EMAIL);
        return author;
    }
}
