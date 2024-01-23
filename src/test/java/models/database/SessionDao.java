package models.database;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SessionDao {
    private Long id;
    private String sessionKey;
    private LocalDateTime createdTime;
    private Long buildNumber;
}
