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

    public Long getProjectId(String projectName) {
        ProjectDao project = getProjectByName(projectName);
        // If project exists, get project id, else create and add project to database
        if (project.getId() == null) {
            project.setName(projectName);
            return insertProject(project);
        } else {
            return project.getId();
        }
    }
}
