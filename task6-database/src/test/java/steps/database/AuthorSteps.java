package steps.database;

import models.database.Author;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorSteps extends BaseSteps {

    public Author getAuthorByLogin(String login) {
        String query = String.format("SELECT * FROM author WHERE login = '%s'", login);
        try (ResultSet resultSet = select(query)) {
            if (resultSet.next()) {
                return new Author(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("login"),
                        resultSet.getString("email")
                );
            } else {
                return new Author();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Long addAuthor(Author author) {
        String name = author.getName();
        String login = author.getLogin();
        String email = author.getEmail();
        String sql = "INSERT INTO author (name, login, email) VALUES (?, ?, ?)";
        return insert(sql, name, login, email);
    }
}
