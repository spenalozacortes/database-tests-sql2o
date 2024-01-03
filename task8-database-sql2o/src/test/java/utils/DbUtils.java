package utils;

import lombok.experimental.UtilityClass;
import org.sql2o.Connection;

import java.util.List;

@UtilityClass
public class DbUtils {

    private final Connection connection = DbConnector.getConnection();

    public static <T> List<T> select(String query, Class<T> model) {
        return connection.createQuery(query)
                .executeAndFetch(model);
    }

    public static Long insert(String query) {
        return (Long) connection.createQuery(query, true)
                .executeUpdate()
                .getKey();
    }

    public static void update(String query) {
        connection.createQuery(query)
                .executeUpdate();
    }

    public static void delete(String query) {
        connection.createQuery(query)
                .executeUpdate();
    }
}
