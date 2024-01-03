package steps.database;

import constants.Queries;
import models.database.TestDao;
import org.sql2o.Connection;
import utils.DbConnector;

import java.math.BigInteger;
import java.util.List;

public class TestSteps {

    private final Connection connection = DbConnector.getConnection();

    public TestDao getTestById(Long id) {
        String query = Queries.GET_TEST_BY_ID.getQuery();
        List<TestDao> tests = connection.createQuery(query)
                .addParameter("id", id)
                .executeAndFetch(TestDao.class);
        return tests.isEmpty() ? null : tests.get(0);
    }

    public List<TestDao> getTests(String digits, int limit) {
        String query = Queries.GET_TESTS.getQuery();
        return connection.createQuery(query)
                .addParameter("digits", digits)
                .addParameter("limit", limit)
                .executeAndFetch(TestDao.class);
    }

    public Long insertTest(TestDao test) {
        String query = Queries.INSERT_TEST.getQuery();
        BigInteger id = (BigInteger) connection.createQuery(query, true)
                .addParameter("name", test.getName())
                .addParameter("statusId", test.getStatusId())
                .addParameter("methodName", test.getMethodName())
                .addParameter("projectId", test.getProjectId())
                .addParameter("sessionId", test.getSessionId())
                .addParameter("startTime", test.getStartTime())
                .addParameter("endTime", test.getEndTime())
                .addParameter("env", test.getEnv())
                .addParameter("browser", test.getBrowser())
                .addParameter("authorId", test.getAuthorId())
                .executeUpdate()
                .getKey();
        return id.longValue();
    }

    public void updateTest(TestDao test) {
        String query = Queries.UPDATE_TEST.getQuery();
        connection.createQuery(query)
                .addParameter("name", test.getName())
                .addParameter("statusId", test.getStatusId())
                .addParameter("methodName", test.getMethodName())
                .addParameter("projectId", test.getProjectId())
                .addParameter("sessionId", test.getSessionId())
                .addParameter("startTime", test.getStartTime())
                .addParameter("endTime", test.getEndTime())
                .addParameter("env", test.getEnv())
                .addParameter("browser", test.getBrowser())
                .addParameter("authorId", test.getAuthorId())
                .addParameter("id", test.getId())
                .executeUpdate();
    }

    public void deleteTest(Long id) {
        String query = Queries.DELETE_TEST.getQuery();
        connection.createQuery(query)
                .addParameter("id", id)
                .executeUpdate();
    }
}
