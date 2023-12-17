package steps.database;

import models.database.ProjectDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectSteps extends BaseSteps {

    public ProjectDAO getProjectByName(String name) {
        String query = String.format("SELECT * FROM project WHERE name = '%s'", name);
        try (ResultSet resultSet = select(query)) {
            if (resultSet.next()) {
                return new ProjectDAO(
                        resultSet.getLong("id"),
                        resultSet.getString("name")
                );
            } else {
                return new ProjectDAO();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Long addProject(ProjectDAO project) {
        String name = project.getName();
        String sql = String.format("INSERT INTO project (name) VALUES ('%s')", name);
        return insert(sql);
    }
}
