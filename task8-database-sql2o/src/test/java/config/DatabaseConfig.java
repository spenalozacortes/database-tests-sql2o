package config;

import lombok.experimental.UtilityClass;

@UtilityClass
public class DatabaseConfig {

    private static final ConfigReader CONFIG_READER = new ConfigReader("src/test/resources/database.json");

    public static String getUrl() {
        return CONFIG_READER.getValueByKey("url").getAsString();
    }

    public static String getUser() {
        return CONFIG_READER.getValueByKey("user").getAsString();
    }

    public static String getPassword() {
        return CONFIG_READER.getValueByKey("password").getAsString();
    }
}
