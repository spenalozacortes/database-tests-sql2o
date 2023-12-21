package constants;

import config.QueriesConfig;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Queries {
    GET_AUTHOR_BY_LOGIN(QueriesConfig.getAuthorByLogin()),
    INSERT_AUTHOR(QueriesConfig.getInsertAuthor()),
    GET_PROJECT_BY_NAME(QueriesConfig.getProjectByName()),
    INSERT_PROJECT(QueriesConfig.getInsertProject()),
    INSERT_SESSION(QueriesConfig.getInsertSession()),
    GET_TEST_BY_ID(QueriesConfig.getTestById()),
    GET_TESTS(QueriesConfig.getTests()),
    INSERT_TEST(QueriesConfig.getInsertTest()),
    UPDATE_TEST(QueriesConfig.getUpdateTest()),
    DELETE_TEST(QueriesConfig.getDeleteTest());

    private final String query;

    public String getQuery(Object... varargs) {
        return String.format(this.query, varargs);
    }
}
