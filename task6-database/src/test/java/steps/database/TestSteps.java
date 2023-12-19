package steps.database;

import constants.Queries;
import models.database.TestDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TestSteps extends BaseSteps {

    public TestDao getTestById(Long id) {
        String query = String.format(Queries.GET_TEST_BY_ID, id);
        try (ResultSet resultSet = select(query)) {
            if (resultSet.next()) {
                return TestDao.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .statusId(resultSet.getInt("status_id"))
                        .methodName(resultSet.getString("method_name"))
                        .projectId(resultSet.getLong("project_id"))
                        .sessionId(resultSet.getLong("session_id"))
                        .startTime(resultSet.getObject("start_time", LocalDateTime.class))
                        .endTime(resultSet.getObject("end_time", LocalDateTime.class))
                        .env(resultSet.getString("env"))
                        .browser(resultSet.getString("browser"))
                        .authorId(resultSet.getLong("author_id"))
                        .build();
            } else {
                return new TestDao();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<TestDao> getTests(String digits, int limit) {
        List<TestDao> tests = new ArrayList<>();
        String query = String.format(Queries.GET_TESTS, digits, limit);
        try (ResultSet resultSet = select(query)) {
            while(resultSet.next()) {
                tests.add(TestDao.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .statusId(resultSet.getInt("status_id"))
                        .methodName(resultSet.getString("method_name"))
                        .projectId(resultSet.getLong("project_id"))
                        .sessionId(resultSet.getLong("session_id"))
                        .startTime(resultSet.getObject("start_time", LocalDateTime.class))
                        .endTime(resultSet.getObject("end_time", LocalDateTime.class))
                        .env(resultSet.getString("env"))
                        .browser(resultSet.getString("browser"))
                        .authorId(resultSet.getLong("author_id"))
                        .build());
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
