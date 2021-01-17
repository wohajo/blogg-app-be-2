package com.prawda.demoBlogBE.domain.post;

import am.ik.yavi.builder.ValidatorBuilder;
import am.ik.yavi.core.Validator;
import com.prawda.demoBlogBE.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostAPIRequest {
    String contents;

    public Post toDomain(User user) {
        return new Post(null, contents, user);
    }

    public static Validator<PostAPIRequest> validator() {
        return ValidatorBuilder.of(PostAPIRequest.class)
                ._string(PostAPIRequest::getContents, "contents", contents -> contents.notNull()
                        .notBlank()
                        .message("Post contents cannot be empty.")
                )
                .build();
    }
}
