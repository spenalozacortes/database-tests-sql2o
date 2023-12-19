package utils;

import lombok.experimental.UtilityClass;
import models.database.AuthorDao;
import models.database.ProjectDao;
import models.database.TestDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@UtilityClass
public class ResultSetUtils {

    public static AuthorDao mapToAuthor(ResultSet resultSet) {
        try {
            if (resultSet.next()) {
                return new AuthorDao(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("login"),
                        resultSet.getString("email")
                );
            } else {
                return new AuthorDao();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ProjectDao mapToProject(ResultSet resultSet) {
        try {
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

    public static TestDao mapToTest(ResultSet resultSet) {
        try {
            if (resultSet.next()) {
                return TestDao.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .statusId(resultSet.getInt("status_id"))
                        .methodName(resultSet.getString("method_name"))
                        .projectId(resultSet.getLong("project_id"))
                        .sessionId(resultSet.getLong("session_id"))
                        .startTime(resultSet.getObject("start_time", LocalDateTime.class))
                        .endTime(resultSet.getObject("end_time", LocalDateTime.class))
                        .env(resultSet.getString("env"))
                        .browser(resultSet.getString("browser"))
                        .authorId(resultSet.getLong("author_id"))
                        .build();
            } else {
                return new TestDao();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
