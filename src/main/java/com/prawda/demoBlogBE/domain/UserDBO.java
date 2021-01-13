package com.prawda.demoBlogBE.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("users")
public class UserDBO {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String passwordHash;
    private String email;
    private Boolean isAdmin;

    public User toDomain() {
        return new User(id, firstName, lastName, username, passwordHash, email, isAdmin);
    }
}