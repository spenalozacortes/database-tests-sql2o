package steps.database;

import utils.DatabaseUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class BaseSteps {

    private final Connection connection = DatabaseUtils.getConnection();

    protected ResultSet select(String query) {
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void update(String sql) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
