package config;

import lombok.experimental.UtilityClass;

@UtilityClass
public class TestDataConfig {
    private static final ConfigReader CONFIG_READER = new ConfigReader("src/test/resources/testData.json");

    public static String getAuthorName() {
        return CONFIG_READER.getValueByKey("name").getAsString();
    }

    public static String getAuthorLogin() {
        return CONFIG_READER.getValueByKey("login").getAsString();
    }

    public static String getAuthorEmail() {
        return CONFIG_READER.getValueByKey("email").getAsString();
    }

    public static String getProjectName() {
        return CONFIG_READER.getValueByKey("project").getAsString();
    }

    public static Long getBuildNumber() {
        return CONFIG_READER.getValueByKey("build_number").getAsLong();
    }
}
