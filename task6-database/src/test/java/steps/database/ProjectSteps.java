package steps.database;

import constants.Queries;
import models.database.ProjectDao;
import utils.ResultSetUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectSteps extends BaseSteps {

    public ProjectDao getProjectByName(String name) {
        String query = String.format(Queries.GET_PROJECT_BY_NAME, name);
        try (ResultSet resultSet = select(query)) {
            return ResultSetUtils.mapToProject(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Long addProject(ProjectDao project) {
        String name = project.getName();
        String sql = String.format(Queries.ADD_PROJECT, name);
        return insert(sql);
    }
}
