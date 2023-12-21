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

    public Long getAuthorId(String login, String authorName, String email) {
        AuthorDao author = getAuthorByLogin(login);
        // If author exists, get author id, else create and add author to database
        if (author.getId() == null) {
            author.setName(authorName);
            author.setLogin(login);
            author.setEmail(email);
            return insertAuthor(author);
        } else {
            return author.getId();
        }
    }
}
