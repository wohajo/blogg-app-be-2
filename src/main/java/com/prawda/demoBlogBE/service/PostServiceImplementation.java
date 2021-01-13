package com.prawda.demoBlogBE.service;

import com.prawda.demoBlogBE.domain.post.Post;
import com.prawda.demoBlogBE.domain.post.PostDBO;
import com.prawda.demoBlogBE.repositories.PostRepository;
import com.prawda.demoBlogBE.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class PostServiceImplementation implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public Mono<Post> findById(Long id) {
        return postRepository
                .findById(id)
                .flatMap(this::withUser);
    }

    @Override
    public Mono<Void> updatePost(Post newPost) {
        return null;
    }

    @Override
    public Flux<Post> getAllPosts() {
        return postRepository
                .findAll()
                .flatMap(this::withUser);
    }

    @Override
    public Mono<Void> remove(String id) {
        return null;
    }

    @Override
    public Flux<Post> findByUserId(Long userId) {
        return postRepository
                .findByUserId(userId)
                .flatMap(this::withUser);
    }

    @Override
    public Flux<Post> findByWord(String givenWord) {
        return null;
    }

    private Mono<Post> withUser(PostDBO postDBO) {
        return userRepository
                .findById(postDBO.getUserId())
                .map(user -> postDBO.toDomain(user.toDomain()));
    }
}
