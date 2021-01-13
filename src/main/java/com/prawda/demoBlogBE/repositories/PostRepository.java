package com.prawda.demoBlogBE.repositories;

import com.prawda.demoBlogBE.domain.PostDBO;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface PostRepository extends ReactiveSortingRepository<PostDBO, Long> {
    Flux<PostDBO> findByUserId(long userId);
}
