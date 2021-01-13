package com.prawda.demoBlogBE.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("posts")
public class PostDBO {
    @Id
    private Long id;
    private String contents;
    private Long userId;

    public Post toDomain(User user) {
        return new Post(id, contents, user);
    }
}
