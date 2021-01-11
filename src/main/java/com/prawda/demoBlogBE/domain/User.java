package com.prawda.demoBlogBE.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private String id;
    @NotNull(message = "First name should not be null.")
    @NotEmpty(message = "First name should not be empty.")
    @Length(min = 3, message = "The field must be at least 3 characters.")
    private String firstName;

    @NotNull(message = "Last name should not be null.")
    @NotEmpty(message = "Last name should not be empty.")
    @Length(min = 3, message = "The field must be at least 3 characters.")
    private String lastName;

    @NotNull(message = "Username should not be null.")
    @NotEmpty(message = "Username should not be empty.")
    @Length(min = 6, message = "The field must be at least 6 characters.")
    private String username;
    private String passwordHash;

    @NotNull(message = "Email should not be null.")
    @NotEmpty(message = "Email should not be empty.")
    @Pattern(regexp = "(\\w)+[@](\\w)+[.](\\w)+", message = "The field must be at least 6 characters.")
    private String email;

    @OneToMany(mappedBy = "poster")
    private List<Post> posts;

    private Boolean isAdmin;

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
