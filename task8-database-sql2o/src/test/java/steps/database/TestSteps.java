package steps.database;

import constants.Queries;
import models.database.TestDao;
import utils.DbUtils;
import utils.UnionReportingConnectionHolder;

import java.util.List;

public class TestSteps {

    private final DbUtils dbUtils = UnionReportingConnectionHolder.getDbUtils();

    public List<TestDao> getTests(String digits, int limit) {
        String query = Queries.GET_TESTS.getQuery(digits, limit);
        return dbUtils.select(query, TestDao.class);
    }

    public TestDao getTestById(Long id) {
        String query = Queries.GET_TEST_BY_ID.getQuery(id);
        return dbUtils.selectFirst(query, TestDao.class);
    }

    public Long insertTest(TestDao test) {
        String query = Queries.INSERT_TEST.getQuery(test.getName(), test.getStatusId(), test.getMethodName(), test.getProjectId(), test.getSessionId(), test.getStartTime(), test.getEndTime(), test.getEnv(), test.getBrowser(), test.getAuthorId());
        return dbUtils.insert(query);
    }

    public void updateTest(TestDao test) {
        String query = Queries.UPDATE_TEST.getQuery(test.getName(), test.getStatusId(), test.getMethodName(), test.getProjectId(), test.getSessionId(), test.getStartTime(), test.getEndTime(), test.getEnv(), test.getBrowser(), test.getAuthorId(), test.getId());
        dbUtils.update(query);
    }

    public void deleteTest(Long id) {
        String query = Queries.DELETE_TEST.getQuery(id);
        dbUtils.update(query);
    }
}
