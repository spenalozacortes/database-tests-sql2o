package utils;

import lombok.experimental.UtilityClass;
import org.sql2o.Connection;

import java.util.List;

@UtilityClass
public class DbUtils {

    private final Connection connection = DbConnector.getConnection();

    public static <T> List<T> select(String query, Class<T> model) {
        return connection.createQuery(query).executeAndFetch(model);
    }

    public static ResultSet insert(String query) {
        try {
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.executeUpdate();
            return statement.getGeneratedKeys();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int update(String query) {
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int delete(String query) {
        try {
            Statement statement = connection.createStatement();
            return statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
