package models.database;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Project {
    private Long id;
    private String name;

    public Project(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
