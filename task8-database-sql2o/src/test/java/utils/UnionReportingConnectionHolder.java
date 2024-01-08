package utils;

import config.DatabaseConfig;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UnionReportingConnectionHolder {

    private static final String URL = DatabaseConfig.getUrl();
    private static final String USER = DatabaseConfig.getUser();
    private static final String PASSWORD = DatabaseConfig.getPassword();
    private static DbUtils dbUtils;

    public static DbUtils getDbUtils() {
        if (dbUtils == null) {
            dbUtils = new DbUtils(URL, USER, PASSWORD);
            return dbUtils;
        }
        return dbUtils;
    }
}
