package models.database;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProjectDao {
    private Long id;
    private String name;

    public ProjectDao(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
