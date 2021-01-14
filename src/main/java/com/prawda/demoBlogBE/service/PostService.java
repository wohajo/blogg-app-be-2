package com.prawda.demoBlogBE.service;

import com.prawda.demoBlogBE.domain.post.Post;
import com.prawda.demoBlogBE.domain.post.PostAPIRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PostService {
    Mono<Post> findById(Long id);
    Mono<Void> updatePost(Post newPost);
    Flux<Post> getAllPosts();
    Mono<Void> remove(String id);
    Flux<Post> findByUserId(Long userId);
    Flux<Post> findByWord(String givenWord);
    Mono<Long> addPost(PostAPIRequest postAPIRequest, String auth);
}
