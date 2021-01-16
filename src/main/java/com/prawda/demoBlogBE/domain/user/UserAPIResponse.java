package com.prawda.demoBlogBE.domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserAPIResponse {
    private Long id;
    private String username;
    private String name;
    private String email;
    private Boolean isAdmin;
}
