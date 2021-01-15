package com.prawda.demoBlogBE.api;

import com.prawda.demoBlogBE.domain.post.Post;
import com.prawda.demoBlogBE.domain.post.PostAPIRequest;
import com.prawda.demoBlogBE.domain.post.PostAPIResponse;
import com.prawda.demoBlogBE.domain.user.UserAPIRequest;
import com.prawda.demoBlogBE.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/posts")
@AllArgsConstructor
public class PostAPI {
    private final PostService postService;

    @GetMapping
    public Flux<PostAPIResponse> getAllPosts() {
        return postService.getAllPosts().map(Post::toAPIResponse);
    }

    @GetMapping("/user/{id}")
    public Flux<PostAPIResponse> getPostsByUserId(@PathVariable Long id) {
        return postService
                .findByUserId(id)
                .map(Post::toAPIResponse);
    }

    @PostMapping
    public Mono<Long> addPost(
            @RequestHeader("Authorization") String auth,
            @RequestBody PostAPIRequest postAPIRequest) {
        return postService.addPost(postAPIRequest, auth);
    }

    @PutMapping("/{id}")
    public Mono<Void> updatePost(
            @RequestHeader("Authorization") String auth,
            @PathVariable Long id,
            @RequestBody PostAPIRequest postAPIRequest) {
        return postService.updatePost(postAPIRequest, id, auth);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deletePost(
            @RequestHeader("Authorization") String auth,
            @PathVariable Long id) {
        return postService.remove(id, auth);
    }
}
