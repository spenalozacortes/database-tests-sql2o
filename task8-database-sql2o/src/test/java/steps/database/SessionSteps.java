package steps.database;

import constants.Queries;
import models.database.SessionDao;
import utils.DbUtils;

public class SessionSteps {

    public Long insertSession(SessionDao session) {
        String query = Queries.INSERT_SESSION.getQuery(session.getSessionKey(), session.getBuildNumber());
        return DbUtils.insert(query);
    }
}
