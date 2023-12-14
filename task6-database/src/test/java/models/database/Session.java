package models.database;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Session {
    private Long id;
    private String session_key;
    private LocalDateTime created_time;
    private Long build_number;
}
