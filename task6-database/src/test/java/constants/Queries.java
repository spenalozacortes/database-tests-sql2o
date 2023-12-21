package constants;

import config.QueriesConfig;
import lombok.Getter;

@Getter
public enum Queries {
    GET_AUTHOR_BY_LOGIN(QueriesConfig.getAuthorByLogin()),
    INSERT_AUTHOR(QueriesConfig.getInsertAuthor()),
    GET_PROJECT_BY_NAME(QueriesConfig.getProjectByName()),
    ADD_PROJECT(QueriesConfig.getAddProject()),
    ADD_SESSION(QueriesConfig.getAddSession()),
    GET_TEST_BY_ID(QueriesConfig.getTestById()),
    GET_TESTS(QueriesConfig.getTests()),
    ADD_TEST(QueriesConfig.getAddTest()),
    UPDATE_TEST(QueriesConfig.getUpdateTest()),
    DELETE_TEST(QueriesConfig.getDeleteTest());

    private final String query;

    Queries(String query) {
        this.query = query;
    }
}
