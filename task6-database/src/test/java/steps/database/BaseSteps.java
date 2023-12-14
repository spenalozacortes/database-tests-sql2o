package steps.database;

import utils.DatabaseUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class BaseSteps {

    protected ResultSet select(String query) {
        try {
            Statement statement = DatabaseUtils.getConnection().createStatement();
            return statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
