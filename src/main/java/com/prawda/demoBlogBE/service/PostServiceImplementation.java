package com.prawda.demoBlogBE.service;

import com.prawda.demoBlogBE.domain.Post;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PostServiceImplementation implements PostService {
    @Override
    public Mono<Post> findById(String id) {
        return null;
    }

    @Override
    public Mono<Void> updatePost(Post newPost) {
        return null;
    }

    @Override
    public Flux<Post> getAllPosts() {
        return null;
    }

    @Override
    public Mono<Void> remove(String id) {
        return null;
    }

    @Override
    public Flux<Post> findByAuthor(String givenAuthorName) {
        return null;
    }

    @Override
    public Flux<Post> findByWord(String givenWord) {
        return null;
    }
}
