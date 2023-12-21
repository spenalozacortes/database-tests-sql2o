package steps.database;

import constants.Queries;
import models.database.TestDao;
import utils.DbUtils;
import utils.ResultSetUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestSteps {

    public TestDao getTestById(Long id) {
        String query = String.format(Queries.GET_TEST_BY_ID.getQuery(), id);
        ResultSet resultSet = DbUtils.select(query);
        return ResultSetUtils.mapToTest(resultSet);
    }

    public List<TestDao> getTests(String digits, int limit) {
        String query = String.format(Queries.GET_TESTS.getQuery(), digits, limit);
        ResultSet resultSet = DbUtils.select(query);
        List<TestDao> tests = new ArrayList<>();
        try {
            while (resultSet.next()) {
                tests.add(ResultSetUtils.mapToTest(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tests;
    }

    public Long insertTest(TestDao test) {
        String query = String.format(Queries.INSERT_TEST.getQuery(), test.getName(), test.getStatusId(), test.getMethodName(), test.getProjectId(), test.getSessionId(), test.getStartTime(), test.getEndTime(), test.getEnv(), test.getBrowser(), test.getAuthorId());
        ResultSet resultSet = DbUtils.insert(query);
        return ResultSetUtils.getIdFromResultSet(resultSet);
    }

    public void updateTest(TestDao test) {
        String query = String.format(Queries.UPDATE_TEST.getQuery(), test.getName(), test.getStatusId(), test.getMethodName(), test.getProjectId(), test.getSessionId(), test.getStartTime(), test.getEndTime(), test.getEnv(), test.getBrowser(), test.getAuthorId(), test.getId());
        DbUtils.update(query);
    }

    public void deleteTest(Long id) {
        String query = String.format(Queries.DELETE_TEST.getQuery(), id);
        DbUtils.delete(query);
    }
}
