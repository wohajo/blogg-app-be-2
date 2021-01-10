package com.prawda.demoBlogBE.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue
    private String id;
    private String contents;
    @ManyToOne
    private User poster;

    public Post(String contents, User poster) {
        this.contents = contents;
        this.poster = poster;
    }
}
