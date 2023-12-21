package steps.database;

import constants.Queries;
import models.database.SessionDao;
import utils.DbUtils;
import utils.ResultSetUtils;

import java.sql.ResultSet;

public class SessionSteps {

    public Long addSession(SessionDao session) {
        String query = String.format(Queries.INSERT_SESSION.getQuery(), session.getSessionKey(), session.getBuildNumber());
        ResultSet resultSet = DbUtils.insert(query);
        return ResultSetUtils.getIdFromResultSet(resultSet);
    }
}
