package com.prawda.demoBlogBE.api;

import com.prawda.demoBlogBE.domain.post.Post;
import com.prawda.demoBlogBE.domain.post.PostAPIResponse;
import com.prawda.demoBlogBE.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("api/posts")
@AllArgsConstructor
public class PostAPI {
    private final PostService postService;

    @GetMapping
    public Flux<PostAPIResponse> getAllPosts() {
        return postService.getAllPosts().map(Post::toAPIResponse);
    }
}
