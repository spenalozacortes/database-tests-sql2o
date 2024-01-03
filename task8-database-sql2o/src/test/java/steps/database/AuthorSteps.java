package steps.database;

import constants.Queries;
import models.database.AuthorDao;
import org.sql2o.Connection;
import utils.DbConnector;

import java.math.BigInteger;
import java.util.List;

public class AuthorSteps {

    private final Connection connection = DbConnector.getConnection();

    public AuthorDao getAuthorByLogin(String login) {
        String query = Queries.GET_AUTHOR_BY_LOGIN.getQuery();
        List<AuthorDao> authors = connection.createQuery(query)
                .addParameter("login", login)
                .executeAndFetch(AuthorDao.class);
        return authors.isEmpty() ? null : authors.get(0);
    }

    public Long insertAuthor(AuthorDao author) {
        String query = Queries.INSERT_AUTHOR.getQuery();
        BigInteger id = (BigInteger) connection.createQuery(query, true)
                .addParameter("name", author.getName())
                .addParameter("login", author.getLogin())
                .addParameter("email", author.getEmail())
                .executeUpdate()
                .getKey();
        return id.longValue();
    }

    public Long insertAuthorIfAbsent(AuthorDao author) {
        AuthorDao authorDao = getAuthorByLogin(author.getLogin());
        if (authorDao == null) {
            return insertAuthor(author);
        }
        return authorDao.getId();
    }
}
