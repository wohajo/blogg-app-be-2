package com.prawda.demoBlogBE.service;

import com.prawda.demoBlogBE.domain.post.Post;
import com.prawda.demoBlogBE.domain.post.PostAPIRequest;
import com.prawda.demoBlogBE.domain.post.PostDBO;
import com.prawda.demoBlogBE.repositories.PostRepository;
import com.prawda.demoBlogBE.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class PostServiceImplementation implements PostService {
    private final PostRepository postRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    @Override
    public Mono<Post> findById(Long id) {
        return postRepository
                .findById(id)
                .flatMap(this::withUser);
    }

    @Override
    public Mono<Void> updatePost(PostAPIRequest postAPIRequest, Long id, String auth) {
        return findById(id)
                .flatMap(post ->
                        userService
                                .authorizeUser(auth)
                                .map(user -> user.getIsAdmin() || post.getUser().equals(user))
                                .filter(foundUser -> foundUser)
                                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not authorized.")))
                                .thenReturn(post.withContents(postAPIRequest.getContents()))
                                .flatMap(updatedPost -> postRepository.save(updatedPost.toDBO()))
                                .then()
                );
    }

    @Override
    public Flux<Post> getAllPosts() {
        return postRepository
                .findAll()
                .flatMap(this::withUser);
    }

    @Override
    public Mono<Void> remove(Long id, String auth) {
        return findById(id)
                .flatMap(post ->
                        userService
                                .authorizeUser(auth)
                                .map(user -> user.getIsAdmin() || post.getUser().equals(user))
                                .filter(foundUser -> foundUser)
                                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not authorized.")))
                                .then(postRepository.delete(post.toDBO()))
                );
    }

    @Override
    public Flux<Post> findByUserId(Long userId, String auth) {
        return userService
                .authorizeUser(auth)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not authorized.")))
                .thenMany(postRepository
                        .findByUserId(userId)
                        .flatMap(this::withUser));
    }

    @Override
    public Flux<Post> findByContents(String givenWord, String auth) {
        return userService
                .authorizeUser(auth)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not authorized.")))
                .thenMany(postRepository
                        .findByContentsContaining(givenWord)
                        .flatMap(this::withUser));
    }

    @Override
    public Mono<Long> addPost(PostAPIRequest postAPIRequest, String auth) {
        return userService
                .authorizeUser(auth)
                .flatMap(user -> Mono.just(postAPIRequest)
                        .map(postAPIRequest1 -> postAPIRequest1.toDomain(user).toDBO()))
                .flatMap(postRepository::save)
                .map(PostDBO::getId);
    }

    private Mono<Post> withUser(PostDBO postDBO) {
        return userRepository
                .findById(postDBO.getUserId())
                .map(user -> postDBO.toDomain(user.toDomain()));
    }
}
