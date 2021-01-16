package com.prawda.demoBlogBE.domain.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostAPIResponse {
    private Long id;
    private Long userId;
    private String contents;
    private String name;
}
