package utils;

import lombok.experimental.UtilityClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@UtilityClass
public class DatabaseUtils {

    private static final String URL = "jdbc:mysql://localhost:3306/union_reporting";
    private static final String USER = "root";
    private static final String PASSWORD = "toor";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
