package utils;

import constants.TableFields;
import lombok.experimental.UtilityClass;
import models.database.AuthorDao;
import models.database.ProjectDao;
import models.database.TestDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class ResultSetUtils {

    public static AuthorDao mapToAuthor(ResultSet resultSet) {
        try {
            AuthorDao author = new AuthorDao();
            if (resultSet.next()) {
                author.setId(resultSet.getLong(TableFields.ID));
                author.setName(resultSet.getString(TableFields.NAME));
                author.setLogin(resultSet.getString(TableFields.LOGIN));
                author.setEmail(resultSet.getString(TableFields.EMAIL));
            }
            return author;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ProjectDao mapToProject(ResultSet resultSet) {
        try {
            ProjectDao project = new ProjectDao();
            if (resultSet.next()) {
                project.setId(resultSet.getLong(TableFields.ID));
                project.setName(resultSet.getString(TableFields.NAME));
            }
            return project;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static TestDao mapToTest(ResultSet resultSet) {
        try {
            if (resultSet.next()) {
                return TestDao.builder()
                        .id(resultSet.getLong(TableFields.ID))
                        .name(resultSet.getString(TableFields.NAME))
                        .statusId(resultSet.getInt(TableFields.STATUS_ID))
                        .methodName(resultSet.getString(TableFields.METHOD_NAME))
                        .projectId(resultSet.getLong(TableFields.PROJECT_ID))
                        .sessionId(resultSet.getLong(TableFields.SESSION_ID))
                        .startTime(String.valueOf(resultSet.getObject(TableFields.START_TIME, LocalDateTime.class).withNano(0)))
                        .endTime(String.valueOf(resultSet.getObject(TableFields.END_TIME, LocalDateTime.class).withNano(0)))
                        .env(resultSet.getString(TableFields.ENV))
                        .browser(resultSet.getString(TableFields.BROWSER))
                        .authorId(resultSet.getLong(TableFields.AUTHOR_ID))
                        .build();
            } else {
                return new TestDao();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Long getIdFromResultSet(ResultSet resultSet) {
        try {
            resultSet.next();
            return resultSet.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<TestDao> getListOfTestsFromResultSet(ResultSet resultSet) {
        List<TestDao> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                list.add(mapToTest(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
