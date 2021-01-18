package com.prawda.demoBlogBE.repositories;

import com.prawda.demoBlogBE.domain.user.UserDBO;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveSortingRepository<UserDBO, Long> {
    Mono<UserDBO> findByName(String name);
    Mono<UserDBO> findByUsername(String username);
    Mono<UserDBO> findByEmail(String email);
    Flux<UserDBO> findByNameOrUsernameOrEmail(String name, String username, String email);
    Mono<UserDBO> findByUsernameAndPasswordHash(String username, String passwordHash);
}
