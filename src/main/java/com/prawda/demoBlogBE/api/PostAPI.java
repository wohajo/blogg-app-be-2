package com.prawda.demoBlogBE.api;

import am.ik.yavi.core.ConstraintViolation;
import com.prawda.demoBlogBE.domain.post.Post;
import com.prawda.demoBlogBE.domain.post.PostAPIRequest;
import com.prawda.demoBlogBE.domain.post.PostAPIResponse;
import com.prawda.demoBlogBE.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@RestController
@RequestMapping("api/posts")
@AllArgsConstructor
public class PostAPI {
    private final PostService postService;

    @GetMapping
    public Flux<PostAPIResponse> getAllPosts() {
        return postService.getAllPosts().map(Post::toAPIResponse);
    }

    @CrossOrigin
    @GetMapping("/user/{id}")
    public Flux<PostAPIResponse> getPostsByUserId(
            @RequestHeader("Authorization") String auth,
            @PathVariable Long id) {
        return postService
                .findByUserId(id, auth)
                .map(Post::toAPIResponse);
    }

    @CrossOrigin
    @PostMapping
    public Mono<Long> addPost(
            @RequestHeader("Authorization") String auth,
            @RequestBody PostAPIRequest postAPIRequest) {
        return PostAPIRequest
                .validator()
                .validateToEither(postAPIRequest)
                .fold(
                        error -> Mono.error(new ResponseStatusException(
                                HttpStatus.BAD_REQUEST,
                                error
                                        .stream()
                                        .map(ConstraintViolation::message)
                                        .filter(s -> !s.contains("null"))
                                        .collect(Collectors.joining("||")))),
                        validatedPost -> postService.addPost(validatedPost, auth)
                );
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public Mono<Void> updatePost(
            @RequestHeader("Authorization") String auth,
            @PathVariable Long id,
            @RequestBody PostAPIRequest postAPIRequest) {
        return PostAPIRequest
                .validator()
                .validateToEither(postAPIRequest)
                .fold(
                        error -> Mono.error(new ResponseStatusException(
                                HttpStatus.BAD_REQUEST,
                                error
                                        .stream()
                                        .map(ConstraintViolation::message)
                                        .filter(s -> !s.contains("null"))
                                        .collect(Collectors.joining("||")))),
                        validatedPost -> postService.updatePost(postAPIRequest, id, auth)
                );
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public Mono<Void> deletePost(
            @RequestHeader("Authorization") String auth,
            @PathVariable Long id) {
        return postService.remove(id, auth);
    }
}
