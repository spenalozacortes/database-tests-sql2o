package steps.database;

import constants.Queries;
import models.database.TestDao;
import utils.DbUtils;

import java.util.List;

public class TestSteps {

    public TestDao getTestById(Long id) {
        String query = Queries.GET_TEST_BY_ID.getQuery(id);
        List<TestDao> tests = DbUtils.select(query, TestDao.class);
        return tests.get(0);
    }

    public List<TestDao> getTests(String digits, int limit) {
        String query = Queries.GET_TESTS.getQuery(digits, limit);
        return DbUtils.select(query, TestDao.class);
    }

    public Long insertTest(TestDao test) {
        String query = Queries.INSERT_TEST.getQuery(test.getName(), test.getStatusId(), test.getMethodName(), test.getProjectId(), test.getSessionId(), test.getStartTime(), test.getEndTime(), test.getEnv(), test.getBrowser(), test.getAuthorId());
        return DbUtils.insert(query);
    }

    public void updateTest(TestDao test) {
        String query = Queries.UPDATE_TEST.getQuery(test.getName(), test.getStatusId(), test.getMethodName(), test.getProjectId(), test.getSessionId(), test.getStartTime(), test.getEndTime(), test.getEnv(), test.getBrowser(), test.getAuthorId(), test.getId());
        DbUtils.update(query);
    }

    public void deleteTest(Long id) {
        String query = Queries.DELETE_TEST.getQuery(id);
        DbUtils.delete(query);
    }
}
