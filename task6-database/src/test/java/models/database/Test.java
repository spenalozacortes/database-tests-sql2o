package models.database;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Test {
    private Long id;
    private String name;
    private Integer status_id;
    private String method_name;
    private Long project_id;
    private Long session_id;
    private LocalDateTime start_time;
    private LocalDateTime end_time;
    private String env;
    private String browser;
    private Long author_id;
}
