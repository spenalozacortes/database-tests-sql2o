package utils;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.math.BigInteger;
import java.util.List;

public class DbUtils {

    private Sql2o sql2o;

    public DbUtils(String url, String user, String password) {
        this.sql2o = new Sql2o(url, user, password);
    }

    public <T> List<T> select(String query, Class<T> model) {
        try (Connection connection = sql2o.open()) {
            return connection.createQuery(query)
                    .executeAndFetch(model);
        }
    }

    public <T> T selectFirst(String query, Class<T> model) {
        try (Connection connection = sql2o.open()) {
            List<T> results = connection.createQuery(query)
                    .executeAndFetch(model);
            return results.isEmpty() ? null : results.get(0);
        }
    }

    public Long insert(String query) {
        try (Connection connection = sql2o.open()) {
            BigInteger id = (BigInteger) connection.createQuery(query, true)
                    .executeUpdate()
                    .getKey();
            return id.longValue();
        }
    }

    public void update(String query) {
        try (Connection connection = sql2o.open()) {
            connection.createQuery(query)
                    .executeUpdate();
        }
    }
}
