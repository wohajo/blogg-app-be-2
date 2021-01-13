package com.prawda.demoBlogBE.domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserAPIRequest {
    private String name;
    private String username;
    private String password;
    private String email;
    private Boolean isAdmin;

    public User toDomain() {
        //TODO hash password
        return new User(null, name, username, password, email, isAdmin);
    }
}
