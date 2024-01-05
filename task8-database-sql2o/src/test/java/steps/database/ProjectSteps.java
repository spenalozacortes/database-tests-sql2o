package steps.database;

import constants.DbParameters;
import constants.Queries;
import models.database.ProjectDao;
import org.sql2o.Connection;
import utils.UnionReportingConnectionHolder;

import java.math.BigInteger;
import java.util.List;

public class ProjectSteps {

    private final Connection connection = UnionReportingConnectionHolder.getConnection();

    public ProjectDao getProjectByName(String name) {
        String query = Queries.GET_PROJECT_BY_NAME.getQuery();
        List<ProjectDao> projects = connection.createQuery(query)
                .addParameter(DbParameters.NAME, name)
                .executeAndFetch(ProjectDao.class);
        return projects.isEmpty() ? null : projects.get(0);
    }

    public Long insertProject(ProjectDao project) {
        String query = Queries.INSERT_PROJECT.getQuery();
        BigInteger id = (BigInteger) connection.createQuery(query, true)
                .addParameter(DbParameters.NAME, project.getName())
                .executeUpdate()
                .getKey();
        return id.longValue();
    }

    public Long insertProjectIfAbsent(ProjectDao project) {
        ProjectDao projectDao = getProjectByName(project.getName());
        if (projectDao == null) {
            return insertProject(project);
        }
        return projectDao.getId();
    }
}
