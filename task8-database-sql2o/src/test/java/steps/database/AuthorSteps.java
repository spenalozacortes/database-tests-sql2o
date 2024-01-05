package steps.database;

import constants.Queries;
import models.database.AuthorDao;
import utils.DbUtils;
import utils.UnionReportingConnectionHolder;

public class AuthorSteps {

    private final DbUtils dbUtils = UnionReportingConnectionHolder.getDbUtils();

    public AuthorDao getAuthorByLogin(String login) {
        String query = Queries.GET_AUTHOR_BY_LOGIN.getQuery(login);
        return dbUtils.selectFirst(query, AuthorDao.class);
    }

    public Long insertAuthor(AuthorDao author) {
        String query = Queries.INSERT_AUTHOR.getQuery(author.getName(), author.getLogin(), author.getEmail());
        return dbUtils.insert(query);
    }

    public Long insertAuthorIfAbsent(AuthorDao author) {
        AuthorDao authorDao = getAuthorByLogin(author.getLogin());
        if (authorDao == null) {
            return insertAuthor(author);
        }
        return authorDao.getId();
    }
}
