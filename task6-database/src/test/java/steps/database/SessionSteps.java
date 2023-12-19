package steps.database;

import constants.Queries;
import models.database.SessionDao;

public class SessionSteps extends BaseSteps {

    public Long addSession(SessionDao session) {
        String sessionKey = session.getSessionKey();
        Long buildNumber = session.getBuildNumber();
        return insert(Queries.ADD_SESSION, sessionKey, buildNumber);
    }
}
