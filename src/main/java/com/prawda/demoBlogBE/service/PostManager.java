package com.prawda.demoBlogBE.service;

import com.prawda.demoBlogBE.domain.Post;

import java.util.List;

public interface PostManager {
    Post addPost(Post post);

    Post findById(String id);

    void updatePost(Post newPost);

    List<Post> getAllPosts();

    void remove(String id);

    List<Post> findByAuthor(String givenAuthorName);

    List<Post> findByWord(String givenWord);

    List<Post> findPosts(String givenAuthorName, String givenWord, String givenId);
}
