package steps.database;

import constants.Queries;
import models.database.TestDao;
import utils.ResultSetUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TestSteps extends BaseSteps {

    public TestDao getTestById(Long id) {
        String query = String.format(Queries.GET_TEST_BY_ID, id);
        try (ResultSet resultSet = select(query)) {
            return ResultSetUtils.mapToTest(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<TestDao> getTests(String digits, int limit) {
        List<TestDao> tests = new ArrayList<>();
        String query = String.format(Queries.GET_TESTS, digits, limit);
        try (ResultSet resultSet = select(query)) {
            while (resultSet.next()) {
                tests.add(ResultSetUtils.mapToTest(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tests;
    }

    public Long addTest(TestDao test) {
        String name = test.getName();
        Integer statusId = test.getStatusId();
        String methodName = test.getMethodName();
        Long projectId = test.getProjectId();
        Long sessionId = test.getSessionId();
        LocalDateTime startTime = test.getStartTime();
        LocalDateTime endTime = test.getEndTime();
        String env = test.getEnv();
        String browser = test.getBrowser();
        Long authorId = test.getAuthorId();

        return insert(Queries.ADD_TEST, name, statusId, methodName, projectId, sessionId, startTime, endTime, env, browser, authorId);
    }

    public void updateTest(TestDao test) {
        Long id = test.getId();
        String name = test.getName();
        Integer statusId = test.getStatusId();
        String methodName = test.getMethodName();
        Long projectId = test.getProjectId();
        Long sessionId = test.getSessionId();
        LocalDateTime startTime = test.getStartTime();
        LocalDateTime endTime = test.getEndTime();
        String env = test.getEnv();
        String browser = test.getBrowser();
        Long authorId = test.getAuthorId();

        update(Queries.UPDATE_TEST, name, statusId, methodName, projectId, sessionId, startTime, endTime, env, browser, authorId, id);
    }

    public void deleteTest(Long id) {
        String query = String.format(Queries.DELETE_TEST, id);
        delete(query);
    }
}
