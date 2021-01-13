package com.prawda.demoBlogBE.service;

import com.prawda.demoBlogBE.domain.Post;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PostService {
    Mono<Post> findById(String id);
    Mono<Void> updatePost(Post newPost);
    Flux<Post> getAllPosts();
    Mono<Void> remove(String id);
    Flux<Post> findByAuthor(String givenAuthorName);
    Flux<Post> findByWord(String givenWord);
}
