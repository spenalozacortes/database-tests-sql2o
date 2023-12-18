package steps.database;

import models.database.ProjectDao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectSteps extends BaseSteps {

    public ProjectDao getProjectByName(String name) {
        String query = String.format("SELECT * FROM project WHERE name = '%s'", name);
        try (ResultSet resultSet = select(query)) {
            if (resultSet.next()) {
                return new ProjectDao(
                        resultSet.getLong("id"),
                        resultSet.getString("name")
                );
            } else {
                return new ProjectDao();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Long addProject(ProjectDao project) {
        String name = project.getName();
        String sql = String.format("INSERT INTO project (name) VALUES ('%s')", name);
        return insert(sql);
    }
}
