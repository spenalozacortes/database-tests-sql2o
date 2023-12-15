package steps.database;

import models.database.TestModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TestSteps extends BaseSteps {

    public List<TestModel> getTests(int digits, int limit) {
        List<TestModel> tests = new ArrayList<>();
        String query = String.format("SELECT * FROM test WHERE id LIKE '%%%d%%' LIMIT %d", digits, limit);
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

                tests.add(new TestModel(id, name, statusId, methodName, projectId, sessionId, start_time, end_time, env, browser, authorId));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tests;
    }

    public void addTest(TestModel test) {
        String name = test.getName();
        Integer statusId = test.getStatus_id();
        String methodName = test.getMethod_name();
        Long projectId = test.getProject_id();
        Long sessionId = test.getSession_id();
        LocalDateTime startTime = test.getStart_time();
        LocalDateTime endTime = test.getEnd_time();
        String env = test.getEnv();
        Long authorId = test.getAuthor_id();

        String sql = String.format("INSERT INTO test (name, status_id, method_name, project_id, session_id, start_time, end_time, env, author_id) VALUES ('%s', %d, '%s', %d, %d, '%s', '%s', '%s', %d)", name, statusId, methodName, projectId, sessionId, startTime, endTime, env, authorId);
        insert(sql);
    }
}
