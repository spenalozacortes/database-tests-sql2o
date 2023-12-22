package steps.database;

import constants.Queries;
import models.database.ProjectDao;
import utils.DbUtils;
import utils.ResultSetUtils;

import java.sql.ResultSet;

public class ProjectSteps {

    public ProjectDao getProjectByName(String name) {
        String query = Queries.GET_PROJECT_BY_NAME.getQuery(name);
        ResultSet resultSet = DbUtils.select(query);
        return ResultSetUtils.mapToProject(resultSet);
    }

    public Long insertProject(ProjectDao project) {
        String query = Queries.INSERT_PROJECT.getQuery(project.getName());
        ResultSet resultSet = DbUtils.insert(query);
        return ResultSetUtils.getIdFromResultSet(resultSet);
    }

    public Long insertProjectIfAbsent(ProjectDao project) {
        ProjectDao projectDao = getProjectByName(project.getName());
        if (projectDao.getId() == null) {
            return insertProject(project);
        }
        return projectDao.getId();
    }
}
