package steps.database;

import constants.Queries;
import models.database.AuthorDao;
import utils.DbUtils;
import utils.ResultSetUtils;

import java.sql.ResultSet;

public class AuthorSteps {

    public AuthorDao getAuthorByLogin(String login) {
        String query = Queries.GET_AUTHOR_BY_LOGIN.getQuery(login);
        ResultSet resultSet = DbUtils.select(query);
        return ResultSetUtils.mapToAuthor(resultSet);
    }

    public Long insertAuthor(AuthorDao author) {
        String query = Queries.INSERT_AUTHOR.getQuery(author.getName(), author.getLogin(), author.getEmail());
        ResultSet resultSet = DbUtils.insert(query);
        return ResultSetUtils.getIdFromResultSet(resultSet);
    }

    public Long insertAuthorIfAbsent(AuthorDao author) {
        AuthorDao authorDao = getAuthorByLogin(author.getLogin());
        if (authorDao.getId() == null) {
            return insertAuthor(author);
        }
        return authorDao.getId();
    }
}
