package models.database;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TestDAO {
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

    public TestDAO(Long id, String name, Integer status_id, String method_name, Long project_id, Long session_id, LocalDateTime start_time, LocalDateTime end_time, String env, String browser, Long author_id) {
        this.id = id;
        this.name = name;
        this.status_id = status_id;
        this.method_name = method_name;
        this.project_id = project_id;
        this.session_id = session_id;
        this.start_time = start_time;
        this.end_time = end_time;
        this.env = env;
        this.browser = browser;
        this.author_id = author_id;
    }
}
