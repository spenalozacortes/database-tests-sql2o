package tests.database;

import models.database.Project;
import org.testng.annotations.Test;
import steps.database.ProjectSteps;

public class DBTests {

    private final ProjectSteps projectSteps = new ProjectSteps();

    @Test
    public void test() {
        Project project = projectSteps.getProjectById(1L);
        System.out.println(project);
    }

}
