package steps.database;

import constants.Queries;
import models.database.SessionDao;
import utils.DbUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SessionSteps {

    public Long addSession(SessionDao session) {
        String sessionKey = session.getSessionKey();
        Long buildNumber = session.getBuildNumber();
        try {
            ResultSet resultSet = DbUtils.insert(Queries.ADD_SESSION.getQuery(), sessionKey, buildNumber);
            resultSet.next();
            return resultSet.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
