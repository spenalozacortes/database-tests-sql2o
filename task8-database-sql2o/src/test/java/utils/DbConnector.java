package utils;

import config.DatabaseConfig;
import lombok.experimental.UtilityClass;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

@UtilityClass
public class DbConnector {

    private static final String URL = DatabaseConfig.getUrl();
    private static final String USER = DatabaseConfig.getUser();
    private static final String PASSWORD = DatabaseConfig.getPassword();
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            Sql2o sql2o = new Sql2o(URL, USER, PASSWORD);
            connection = sql2o.open();
            return connection;
        }
        return connection;
    }

    public static void closeConnection() {
        connection.close();
        connection = null;
    }
}
