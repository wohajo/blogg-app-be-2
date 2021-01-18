package com.prawda.demoBlogBE.repositories;

import com.prawda.demoBlogBE.domain.post.PostDBO;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface PostRepository extends ReactiveSortingRepository<PostDBO, Long> {
    Flux<PostDBO> findByUserId(long userId);
    Flux<PostDBO> findByContentsContaining(String givenWord);
}
