package models.database;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
}
