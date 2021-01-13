package com.prawda.demoBlogBE.domain.post;

import com.prawda.demoBlogBE.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostAPIRequest {
    String contents;

    public Post toDomain(User user) {
        return new Post(null, contents, user);
    }
}
