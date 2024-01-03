package steps.database;

import constants.DbParameters;
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
                .addParameter(DbParameters.ID, id)
                .executeAndFetch(TestDao.class);
        return tests.isEmpty() ? null : tests.get(0);
    }

    public List<TestDao> getTests(String digits, int limit) {
        String query = Queries.GET_TESTS.getQuery();
        return connection.createQuery(query)
                .addParameter(DbParameters.DIGITS, digits)
                .addParameter(DbParameters.LIMIT, limit)
                .executeAndFetch(TestDao.class);
    }

    public Long insertTest(TestDao test) {
        String query = Queries.INSERT_TEST.getQuery();
        BigInteger id = (BigInteger) connection.createQuery(query, true)
                .addParameter(DbParameters.NAME, test.getName())
                .addParameter(DbParameters.STATUS_ID, test.getStatusId())
                .addParameter(DbParameters.METHOD_NAME, test.getMethodName())
                .addParameter(DbParameters.PROJECT_ID, test.getProjectId())
                .addParameter(DbParameters.SESSION_ID, test.getSessionId())
                .addParameter(DbParameters.START_TIME, test.getStartTime())
                .addParameter(DbParameters.END_TIME, test.getEndTime())
                .addParameter(DbParameters.ENV, test.getEnv())
                .addParameter(DbParameters.BROWSER, test.getBrowser())
                .addParameter(DbParameters.AUTHOR_ID, test.getAuthorId())
                .executeUpdate()
                .getKey();
        return id.longValue();
    }

    public void updateTest(TestDao test) {
        String query = Queries.UPDATE_TEST.getQuery();
        connection.createQuery(query)
                .addParameter(DbParameters.NAME, test.getName())
                .addParameter(DbParameters.STATUS_ID, test.getStatusId())
                .addParameter(DbParameters.METHOD_NAME, test.getMethodName())
                .addParameter(DbParameters.PROJECT_ID, test.getProjectId())
                .addParameter(DbParameters.SESSION_ID, test.getSessionId())
                .addParameter(DbParameters.START_TIME, test.getStartTime())
                .addParameter(DbParameters.END_TIME, test.getEndTime())
                .addParameter(DbParameters.ENV, test.getEnv())
                .addParameter(DbParameters.BROWSER, test.getBrowser())
                .addParameter(DbParameters.AUTHOR_ID, test.getAuthorId())
                .addParameter(DbParameters.ID, test.getId())
                .executeUpdate();
    }

    public void deleteTest(Long id) {
        String query = Queries.DELETE_TEST.getQuery();
        connection.createQuery(query)
                .addParameter(DbParameters.ID, id)
                .executeUpdate();
    }
}
