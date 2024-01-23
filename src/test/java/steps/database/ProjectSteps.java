package steps.database;

import constants.Queries;
import models.database.ProjectDao;
import utils.DbUtils;
import utils.UnionReportingConnectionHolder;

public class ProjectSteps {

    private final DbUtils dbUtils = UnionReportingConnectionHolder.getDbUtils();

    public ProjectDao getProjectByName(String name) {
        String query = Queries.GET_PROJECT_BY_NAME.getQuery(name);
        return dbUtils.selectFirst(query, ProjectDao.class);
    }

    public Long insertProject(ProjectDao project) {
        String query = Queries.INSERT_PROJECT.getQuery(project.getName());
        return dbUtils.insert(query);
    }

    public Long insertProjectIfAbsent(ProjectDao project) {
        ProjectDao projectDao = getProjectByName(project.getName());
        if (projectDao == null) {
            return insertProject(project);
        }
        return projectDao.getId();
    }
}
