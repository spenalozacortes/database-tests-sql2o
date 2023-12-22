package data;

import config.TestDataConfig;
import lombok.experimental.UtilityClass;
import models.database.SessionDao;
import utils.RandomUtils;

@UtilityClass
public class SessionGenerator {

    private static final Long BUILD_NUMBER = TestDataConfig.getBuildNumber();

    public static SessionDao generateSession() {
        SessionDao session = new SessionDao();
        session.setSessionKey(RandomUtils.generateSessionId());
        session.setBuildNumber(BUILD_NUMBER);
        return session;
    }
}
