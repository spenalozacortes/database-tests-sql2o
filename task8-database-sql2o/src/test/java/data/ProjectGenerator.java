package data;

import config.TestDataConfig;
import lombok.experimental.UtilityClass;
import models.database.ProjectDao;

@UtilityClass
public class ProjectGenerator {

    private static final String PROJECT_NAME = TestDataConfig.getProjectName();

    public static ProjectDao generateProject() {
        ProjectDao project = new ProjectDao();
        project.setName(PROJECT_NAME);
        return project;
    }
}
