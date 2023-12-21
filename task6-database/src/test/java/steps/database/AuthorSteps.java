package steps.database;

import constants.Queries;
import models.database.AuthorDao;
import utils.DbUtils;
import utils.ResultSetUtils;

import java.sql.ResultSet;

public class AuthorSteps {

    public AuthorDao getAuthorByLogin(String login) {
        String query = String.format(Queries.GET_AUTHOR_BY_LOGIN.getQuery(), login);
        ResultSet resultSet = DbUtils.select(query);
        return ResultSetUtils.mapToAuthor(resultSet);
    }

    public Long insertAuthor(AuthorDao author) {
        String query = String.format(Queries.INSERT_AUTHOR.getQuery(), author.getName(), author.getLogin(), author.getEmail());
        ResultSet resultSet = DbUtils.insert(query);
        return ResultSetUtils.getIdFromResultSet(resultSet);
    }
}
