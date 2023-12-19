package constants;

import config.QueriesConfig;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Queries {

    public static final String GET_AUTHOR_BY_LOGIN = QueriesConfig.getAuthorByLogin();
    public static final String ADD_AUTHOR = QueriesConfig.getAddAuthor();
    public static final String GET_PROJECT_BY_NAME = QueriesConfig.getProjectByName();
    public static final String ADD_PROJECT = QueriesConfig.getAddProject();
    public static final String ADD_SESSION = QueriesConfig.getAddSession();
    public static final String GET_TEST_BY_ID = QueriesConfig.getTestById();
    public static final String GET_TESTS = QueriesConfig.getTests();
    public static final String ADD_TEST = QueriesConfig.getAddTest();
    public static final String UPDATE_TEST = QueriesConfig.getUpdateTest();
    public static final String DELETE_TEST = QueriesConfig.getDeleteTest();
}
