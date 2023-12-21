package utils;

import lombok.experimental.UtilityClass;

import java.sql.*;

@UtilityClass
public class DbUtils {

    private final Connection connection = DbConnector.getConnection();

    public static ResultSet select(String query) {
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

    public static void update(String query) {
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void delete(String query) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
