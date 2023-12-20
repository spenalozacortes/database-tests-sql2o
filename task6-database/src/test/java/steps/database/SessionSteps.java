package steps.database;

import constants.Queries;
import models.database.SessionDao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SessionSteps extends BaseSteps {

    public Long addSession(SessionDao session) {
        String sessionKey = session.getSessionKey();
        Long buildNumber = session.getBuildNumber();
        try {
            ResultSet resultSet = insert(Queries.ADD_SESSION.getQuery(), sessionKey, buildNumber);
            resultSet.next();
            return resultSet.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
