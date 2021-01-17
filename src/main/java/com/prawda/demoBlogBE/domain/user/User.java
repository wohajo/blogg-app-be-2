package com.prawda.demoBlogBE.domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private Long id;
    private String name;
    private String username;
    private String passwordHash;
    private String email;
    private Boolean isAdmin;
    public UserDBO toDBO() {
        return new UserDBO(id, name, username, passwordHash, email, isAdmin);
    }

    public UserAPIResponse toAPIResponse() {
        return new UserAPIResponse(id, username, name, email, isAdmin);
    }
}
