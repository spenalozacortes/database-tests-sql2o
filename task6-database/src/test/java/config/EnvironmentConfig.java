package config;

import lombok.experimental.UtilityClass;
import utils.SessionUtils;

@UtilityClass
public class EnvironmentConfig {

    private static final ConfigReader CONFIG_READER = new ConfigReader("src/test/resources/environment.json");
    private static final Long AUTHOR_ID = SessionUtils.getAuthorId();
    private static final Long SESSION_ID = SessionUtils.getSessionId();
    private static final Long PROJECT_ID = SessionUtils.getProjectId();
    private static final String ENV = System.getenv("COMPUTERNAME");


    public static String getBaseUri() {
        return CONFIG_READER.getValueByKey("baseURI").getAsString();
    }

    public static Long getAuthorId() {
        return AUTHOR_ID;
    }

    public static Long getSessionId() {
        return SESSION_ID;
    }

    public static Long getProjectId() {
        return PROJECT_ID;
    }

    public static String getEnv() {
        return ENV;
    }
}
