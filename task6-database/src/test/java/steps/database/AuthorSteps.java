package steps.database;

import constants.Queries;
import models.database.AuthorDao;
import utils.ResultSetUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorSteps extends BaseSteps {

    public AuthorDao getAuthorByLogin(String login) {
        String query = String.format(Queries.GET_AUTHOR_BY_LOGIN, login);
        try (ResultSet resultSet = select(query)) {
            return ResultSetUtils.mapToAuthor(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Long addAuthor(AuthorDao author) {
        String name = author.getName();
        String login = author.getLogin();
        String email = author.getEmail();
        return insert(Queries.ADD_AUTHOR, name, login, email);
    }
}
