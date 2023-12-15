package steps.database;

import models.database.Project;
import models.database.Session;

public class SessionSteps extends BaseSteps {

    public void addSession(Session session) {
        String sessionKey = session.getSession_key();
        Long buildNumber = session.getBuild_number();
        String sql = String.format("INSERT INTO session (session_key, build_number) VALUES ('%s', %d)", sessionKey, buildNumber);
        update(sql);
    }
}
