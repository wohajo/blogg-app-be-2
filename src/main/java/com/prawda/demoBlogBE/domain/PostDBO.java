package com.prawda.demoBlogBE.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("post")
public class PostDBO {
    private Long id;
    private String contents;
    private Long userId;
}
