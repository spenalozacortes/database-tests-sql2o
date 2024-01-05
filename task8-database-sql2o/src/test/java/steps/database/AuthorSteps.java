package steps.database;

import constants.DbParameters;
import constants.Queries;
import models.database.AuthorDao;
import org.sql2o.Connection;
import utils.UnionReportingConnectionHolder;

import java.math.BigInteger;
import java.util.List;

public class AuthorSteps {

    private final Connection connection = UnionReportingConnectionHolder.getConnection();

    public AuthorDao getAuthorByLogin(String login) {
        String query = Queries.GET_AUTHOR_BY_LOGIN.getQuery();
        List<AuthorDao> authors = connection.createQuery(query)
                .addParameter(DbParameters.LOGIN, login)
                .executeAndFetch(AuthorDao.class);
        return authors.isEmpty() ? null : authors.get(0);
    }

    public Long insertAuthor(AuthorDao author) {
        String query = Queries.INSERT_AUTHOR.getQuery();
        BigInteger id = (BigInteger) connection.createQuery(query, true)
                .addParameter(DbParameters.NAME, author.getName())
                .addParameter(DbParameters.LOGIN, author.getLogin())
                .addParameter(DbParameters.EMAIL, author.getEmail())
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
