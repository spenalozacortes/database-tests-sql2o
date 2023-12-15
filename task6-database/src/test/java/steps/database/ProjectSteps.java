package steps.database;

import models.database.Project;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;

public class ProjectSteps extends BaseSteps {

    public Project getProjectById(Long id) {
        String query = String.format("SELECT * FROM project WHERE id = %d", id);
        try (ResultSet resultSet = select(query)) {
            if (resultSet.next()) {
                return new Project(
                        resultSet.getLong("id"),
                        resultSet.getString("name")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        throw new NoSuchElementException(String.format("Project with id %d not found", id));
    }

    public void addProject(Project project) {
        String name = project.getName();
        String sql = String.format("INSERT INTO project (name) VALUES ('%s')", name);
        insert(sql);
    }

    public void deleteProject(Long id) {
        String sql = String.format("DELETE FROM project WHERE id = %d", id);
        delete(sql);
    }
}
