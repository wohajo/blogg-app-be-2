package com.prawda.demoBlogBE.domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAPIRequest {
    @NotNull(message = "Name should not be null.")
    @NotEmpty(message = "Name should not be empty.")
    @Length(min = 3, max = 150, message = "The field must be at least 6 characters.")
    private String name;
    @NotNull(message = "Username should not be null.")
    @NotEmpty(message = "Username should not be empty.")
    @Length(min = 6, max = 150, message = "The field must be at least 6 characters.")
    private String username;
    private String password;
    @NotNull(message = "Email should not be null.")
    @NotEmpty(message = "Email should not be empty.")
    @Pattern(regexp = "(\\w)+[@](\\w)+[.](\\w)+", message = "The field must be at least 6 characters.")
    @Length(min = 6, max = 60)
    private String email;
    private Boolean isAdmin;

    public User toDomain() {
        return new User(null, name, username, DigestUtils.sha512Hex(password), email, isAdmin);
    }
}
