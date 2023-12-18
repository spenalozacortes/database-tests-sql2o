package models.database;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TestDao {
    private Long id;
    private String name;
    private Integer statusId;
    private String methodName;
    private Long projectId;
    private Long sessionId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String env;
    private String browser;
    private Long authorId;

    public TestDao(Long id, String name, Integer statusId, String methodName, Long projectId, Long sessionId, LocalDateTime startTime, LocalDateTime endTime, String env, String browser, Long authorId) {
        this.id = id;
        this.name = name;
        this.statusId = statusId;
        this.methodName = methodName;
        this.projectId = projectId;
        this.sessionId = sessionId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.env = env;
        this.browser = browser;
        this.authorId = authorId;
    }
}
