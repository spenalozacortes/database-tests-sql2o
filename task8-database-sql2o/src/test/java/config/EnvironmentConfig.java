package config;

import lombok.experimental.UtilityClass;

@UtilityClass
public class EnvironmentConfig {

    private static final ConfigReader CONFIG_READER = new ConfigReader("src/test/resources/environment.json");

    public static String getBaseUri() {
        return CONFIG_READER.getValueByKey("baseURI").getAsString();
    }

    public static String getEnv() {
        return System.getenv("COMPUTERNAME");
    }

    public static String getBrowser() {
        return CONFIG_READER.getValueByKey("browser").getAsString();
    }
}
