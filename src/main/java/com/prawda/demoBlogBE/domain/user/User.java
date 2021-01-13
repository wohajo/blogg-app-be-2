package com.prawda.demoBlogBE.domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private Long id;

    @NotNull(message = "First name should not be null.")
    @NotEmpty(message = "First name should not be empty.")
    @Length(min = 3, max = 60, message = "The field must be at least 3 characters.")
    private String name;

    @NotNull(message = "Username should not be null.")
    @NotEmpty(message = "Username should not be empty.")
    @Length(min = 6, max = 150, message = "The field must be at least 6 characters.")
    private String username;

    private String passwordHash;

    @NotNull(message = "Email should not be null.")
    @NotEmpty(message = "Email should not be empty.")
    @Pattern(regexp = "(\\w)+[@](\\w)+[.](\\w)+", message = "The field must be at least 6 characters.")
    @Length(max = 60)
    private String email;

    private Boolean isAdmin;

    public UserDBO toDBO() {
        return new UserDBO(id, name, username, passwordHash, email, isAdmin);
    }

    public UserAPIResponse toAPIResponse() {
        return new UserAPIResponse(id, name, email, isAdmin);
    }
}
