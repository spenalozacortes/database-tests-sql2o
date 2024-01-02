package models.database;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestDao {
    private Long id;
    private String name;
    private Integer statusId;
    private String methodName;
    private Long projectId;
    private Long sessionId;
    private String startTime;
    private String endTime;
    private String env;
    private String browser;
    private Long authorId;
}
