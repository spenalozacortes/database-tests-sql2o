package steps.database;

import constants.Queries;
import models.database.AuthorDao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorSteps extends BaseSteps {

    public AuthorDao getAuthorByLogin(String login) {
        String query = String.format(Queries.GET_AUTHOR_BY_LOGIN, login);
        try (ResultSet resultSet = select(query)) {
            if (resultSet.next()) {
                return new AuthorDao(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("login"),
                        resultSet.getString("email")
                );
            } else {
                return new AuthorDao();
            }
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
