package steps.database;

import constants.DbParameters;
import constants.Queries;
import models.database.SessionDao;
import org.sql2o.Connection;
import utils.UnionReportingConnectionHolder;

import java.math.BigInteger;

public class SessionSteps {

    private final Connection connection = UnionReportingConnectionHolder.getConnection();

    public Long insertSession(SessionDao session) {
        String query = Queries.INSERT_SESSION.getQuery();
        BigInteger id = (BigInteger) connection.createQuery(query, true)
                .addParameter(DbParameters.SESSION_KEY, session.getSessionKey())
                .addParameter(DbParameters.BUILD_NUMBER, session.getBuildNumber())
                .executeUpdate()
                .getKey();
        return id.longValue();
    }
}
