package com.prawda.demoBlogBE.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private String id;
    private String firstName;
    private String lastName;
    private String username;
    private String passwordHash;
    private String email;
    @OneToMany(mappedBy = "poster")
    private List<Post> posts;

    public User(String firstName,
                String lastName,
                String username,
                String passwordHash,
                String email,
                List<Post> posts) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.passwordHash = passwordHash;
        this.email = email;
        this.posts = posts;
    }
}
