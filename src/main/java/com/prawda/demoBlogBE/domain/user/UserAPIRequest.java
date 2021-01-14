package com.prawda.demoBlogBE.domain.user;

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
}
