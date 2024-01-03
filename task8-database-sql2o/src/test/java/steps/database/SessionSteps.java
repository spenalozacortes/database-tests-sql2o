package steps.database;

import constants.Queries;
import models.database.SessionDao;
import org.sql2o.Connection;
import utils.DbConnector;

import java.math.BigInteger;

public class SessionSteps {

    private final Connection connection = DbConnector.getConnection();

    public Long insertSession(SessionDao session) {
        String query = Queries.INSERT_SESSION.getQuery();
        BigInteger id = (BigInteger) connection.createQuery(query, true)
                .addParameter("sessionKey", session.getSessionKey())
                .addParameter("buildNumber", session.getBuildNumber())
                .executeUpdate()
                .getKey();
        return id.longValue();
    }
}
