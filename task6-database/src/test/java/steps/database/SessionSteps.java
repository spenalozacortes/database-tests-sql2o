package steps.database;

import models.database.SessionDao;

public class SessionSteps extends BaseSteps {

    public Long addSession(SessionDao session) {
        String sessionKey = session.getSessionKey();
        Long buildNumber = session.getBuildNumber();
        String sql = "INSERT INTO session (session_key, build_number) VALUES (?, ?)";
        return insert(sql, sessionKey, buildNumber);
    }
}
