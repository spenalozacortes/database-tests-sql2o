package steps.database;

import models.database.Test;

public class TestSteps extends BaseSteps {

    public void addTest(Test test) {
        String name = test.getName();
        String methodName = test.getMethod_name();
        Long projectId = test.getProject_id();
        Long sessionId = test.getSession_id();
        String env = test.getEnv();
        String sql = String.format("INSERT INTO test (name, method_name, project_id, session_id, env) VALUES ('%s', '%s', %d, %d, '%s')", name, methodName, projectId, sessionId, env);
        update(sql);
    }
}
