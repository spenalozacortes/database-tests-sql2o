package steps.database;

import models.database.SessionDAO;

public class SessionSteps extends BaseSteps {

    public Long addSession(SessionDAO session) {
        String sessionKey = session.getSession_key();
        Long buildNumber = session.getBuild_number();
        String sql = "INSERT INTO session (session_key, build_number) VALUES (?, ?)";
        return insert(sql, sessionKey, buildNumber);
    }
}
