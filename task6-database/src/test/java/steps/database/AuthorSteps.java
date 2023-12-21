package steps.database;

import constants.Queries;
import models.database.AuthorDao;
import utils.DbUtils;
import utils.ResultSetUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorSteps {

    public AuthorDao getAuthorByLogin(String login) {
        String query = String.format(Queries.GET_AUTHOR_BY_LOGIN.getQuery(), login);
        try (ResultSet resultSet = DbUtils.select(query)) {
            return ResultSetUtils.mapToAuthor(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Long addAuthor(AuthorDao author) {
        String name = author.getName();
        String login = author.getLogin();
        String email = author.getEmail();
        try {
            ResultSet resultSet = DbUtils.insert(Queries.ADD_AUTHOR.getQuery(), name, login, email);
            resultSet.next();
            return resultSet.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
