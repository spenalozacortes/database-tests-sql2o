package steps.database;

import models.database.TestDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TestSteps extends BaseSteps {

    public TestDAO getTestById(Long id) {
        String query = String.format("SELECT * FROM test WHERE id = %d", id);
        try (ResultSet resultSet = select(query)) {
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                Integer statusId = resultSet.getInt("status_id");
                String methodName = resultSet.getString("method_name");
                Long projectId = resultSet.getLong("project_id");
                Long sessionId = resultSet.getLong("session_id");
                LocalDateTime start_time = resultSet.getObject("start_time", LocalDateTime.class);
                LocalDateTime end_time = resultSet.getObject("end_time", LocalDateTime.class);
                String env = resultSet.getString("env");
                String browser = resultSet.getString("browser");
                Long authorId = resultSet.getLong("author_id");

                return new TestDAO(id, name, statusId, methodName, projectId, sessionId, start_time, end_time, env, browser, authorId);
            } else {
                return new TestDAO();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<TestDAO> getTests(String digits, int limit) {
        List<TestDAO> tests = new ArrayList<>();
        String query = String.format("SELECT * FROM test WHERE id LIKE '%%%s%%' LIMIT %d", digits, limit);
        try (ResultSet resultSet = select(query)) {
            while(resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                Integer statusId = resultSet.getInt("status_id");
                String methodName = resultSet.getString("method_name");
                Long projectId = resultSet.getLong("project_id");
                Long sessionId = resultSet.getLong("session_id");
                LocalDateTime start_time = resultSet.getObject("start_time", LocalDateTime.class);
                LocalDateTime end_time = resultSet.getObject("end_time", LocalDateTime.class);
                String env = resultSet.getString("env");
                String browser = resultSet.getString("browser");
                Long authorId = resultSet.getLong("author_id");

                tests.add(new TestDAO(id, name, statusId, methodName, projectId, sessionId, start_time, end_time, env, browser, authorId));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tests;
    }

    public Long addTest(TestDAO test) {
        String name = test.getName();
        Integer statusId = test.getStatus_id();
        String methodName = test.getMethod_name();
        Long projectId = test.getProject_id();
        Long sessionId = test.getSession_id();
        LocalDateTime startTime = test.getStart_time();
        LocalDateTime endTime = test.getEnd_time();
        String env = test.getEnv();
        String browser = test.getBrowser();
        Long authorId = test.getAuthor_id();

        String sql = "INSERT INTO test (name, status_id, method_name, project_id, session_id, start_time, end_time, env, browser, author_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return insert(sql, name, statusId, methodName, projectId, sessionId, startTime, endTime, env, browser, authorId);
    }

    public void updateTest(TestDAO test) {
        Long id = test.getId();
        String name = test.getName();
        Integer statusId = test.getStatus_id();
        String methodName = test.getMethod_name();
        Long projectId = test.getProject_id();
        Long sessionId = test.getSession_id();
        LocalDateTime startTime = test.getStart_time();
        LocalDateTime endTime = test.getEnd_time();
        String env = test.getEnv();
        String browser = test.getBrowser();
        Long authorId = test.getAuthor_id();

        String sql = "UPDATE test SET name = ?, status_id = ?, method_name = ?, project_id = ?, session_id = ?, start_time = ?, end_time = ?, env = ?, browser = ?, author_id = ? WHERE id = ?";
        update(sql, name, statusId, methodName, projectId, sessionId, startTime, endTime, env, browser, authorId, id);
    }

    public void deleteTest(Long id) {
        String query = String.format("DELETE FROM test WHERE id = %d", id);
        delete(query);
    }
}
