package steps.database;

import models.database.Test;

import java.time.LocalDateTime;

public class TestSteps extends BaseSteps {

    public void addTest(Test test) {
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
