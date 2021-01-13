package com.prawda.demoBlogBE.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    private Long id;
    private String contents;
    private User user;

    public PostDBO toDBO() {
        return new PostDBO(id, contents, user.getId());
    }

    public PostAPIResponse toAPIResponse() {
        return new PostAPIResponse(id, contents, user.getName());
    }
}
