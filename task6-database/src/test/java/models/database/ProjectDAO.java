package models.database;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProjectDAO {
    private Long id;
    private String name;

    public ProjectDAO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
