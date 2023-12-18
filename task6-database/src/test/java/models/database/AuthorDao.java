package models.database;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthorDao {
    private Long id;
    private String name;
    private String login;
    private String email;

    public AuthorDao(Long id, String name, String login, String email) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
    }
}
