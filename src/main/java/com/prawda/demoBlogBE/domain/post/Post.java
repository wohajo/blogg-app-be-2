package com.prawda.demoBlogBE.domain.post;

import com.prawda.demoBlogBE.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@With
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
