package steps.database;

import constants.Queries;
import models.database.SessionDao;
import utils.DbUtils;
import utils.UnionReportingConnectionHolder;

public class SessionSteps {

    private final DbUtils dbUtils = UnionReportingConnectionHolder.getDbUtils();

    public Long insertSession(SessionDao session) {
        String query = Queries.INSERT_SESSION.getQuery(session.getSessionKey(), session.getBuildNumber());
        return dbUtils.insert(query);
    }
}
