package com.prawda.demoBlogBE.domain.user;

import am.ik.yavi.builder.ValidatorBuilder;
import am.ik.yavi.core.Validator;
import am.ik.yavi.core.ViolationMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAPIRequest {
    private String name;
    private String username;
    private String password;
    private String email;
    private Boolean isAdmin;

    public User toDomain() {
        return new User(null, name, username, DigestUtils.sha512Hex(password), email, isAdmin);
    }

    public static Validator<UserAPIRequest> validator() {
        return ValidatorBuilder.of(UserAPIRequest.class)
                ._string(UserAPIRequest::getName, "name", name -> name.notNull()
                        .notBlank()
                        .message("Name cannot be empty and must be between 3 and 60 characters.")
                        .greaterThanOrEqual(3)
                        .lessThanOrEqual(60)
                        .message("Name cannot be empty and must be between 3 and 60 characters.")
                )
                ._string(UserAPIRequest::getUsername, "username", username -> username.notNull()
                        .notBlank()
                        .message("Username cannot be empty and must be between 3 and 60 characters.")
                        .greaterThanOrEqual(3)
                        .lessThanOrEqual(60)
                        .message("Username cannot be empty and must be between 3 and 60 characters.")
                        .predicate(s -> !s.contains("Admin") || !s.contains("admin"),
                                ViolationMessage.of(
                                        "Username",
                                        "Nice try, but You cannot name yourself admin."
                                )
                        )
                        .message("Nice try, but You cannot name yourself admin.")
                        .predicate(s -> !s.contains("~"),
                                ViolationMessage.of(
                                        "Username",
                                        "Username cannot contain \"~\"."
                                )
                        )
                )
                ._string(UserAPIRequest::getPassword, "password", password -> password.notNull()
                        .notBlank()
                        .message("Password cannot be empty.")
                        .greaterThanOrEqual(8)
                        .lessThanOrEqual(60)
                        .message("Password must be between 8 and 60 characters.")
                )
                ._string(UserAPIRequest::getEmail, "email", email -> email.notNull()
                        .notBlank()
                        .message("Email cannot be empty.")
                        .greaterThanOrEqual(3)
                        .lessThanOrEqual(60)
                        .message("Email must be between 3 and 60 characters.")
                        .pattern("[\\w]+[@][\\w]+[.][\\w]+")
                        .message("Email must have format like [x]@[y].[z]")
                )
                .build();
    }
}
