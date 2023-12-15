package models.database;

import lombok.Data;

@Data
public class Author {
    Long id;
    String name;
    String login;
    String email;
}
