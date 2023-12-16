package models.database;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthorDAO {
    Long id;
    String name;
    String login;
    String email;

    public AuthorDAO(Long id, String name, String login, String email) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
    }
}
