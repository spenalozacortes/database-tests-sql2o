package config;

import lombok.experimental.UtilityClass;

@UtilityClass
public class QueriesConfig {

    private static final ConfigReader CONFIG_READER = new ConfigReader("src/test/resources/queries.json");

    public static String getAuthorByLogin() {
        return CONFIG_READER.getValueByKey("getAuthorByLogin").getAsString();
    }

    public static String getInsertAuthor() {
        return CONFIG_READER.getValueByKey("insertAuthor").getAsString();
    }

    public static String getProjectByName() {
        return CONFIG_READER.getValueByKey("getProjectByName").getAsString();
    }

    public static String getInsertProject() {
        return CONFIG_READER.getValueByKey("insertProject").getAsString();
    }

    public static String getInsertSession() {
        return CONFIG_READER.getValueByKey("insertSession").getAsString();
    }

    public static String getTestById() {
        return CONFIG_READER.getValueByKey("getTestById").getAsString();
    }

    public static String getTests() {
        return CONFIG_READER.getValueByKey("getTests").getAsString();
    }

    public static String getInsertTest() {
        return CONFIG_READER.getValueByKey("insertTest").getAsString();
    }

    public static String getUpdateTest() {
        return CONFIG_READER.getValueByKey("updateTest").getAsString();
    }

    public static String getDeleteTest() {
        return CONFIG_READER.getValueByKey("deleteTest").getAsString();
    }
}
