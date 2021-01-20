package com.prawda.demoBlogBE.service;

import com.prawda.demoBlogBE.domain.post.Post;
import com.prawda.demoBlogBE.domain.post.PostAPIRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PostService {
    Mono<Post> findById(Long id);
    Mono<Void> updatePost(PostAPIRequest postAPIRequest, Long id, String auth);
    Flux<Post> getAllPosts();
    Mono<Void> remove(Long id, String auth);
    Flux<Post> findByUserId(Long userId, String auth);
    Flux<Post> findLongestPosts(String auth);
    Flux<Post> findByContents(String givenWord, String auth);
    Mono<Long> addPost(PostAPIRequest postAPIRequest, String auth);
}
