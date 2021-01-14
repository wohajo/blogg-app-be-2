package com.prawda.demoBlogBE.service;

import com.prawda.demoBlogBE.domain.user.User;
import com.prawda.demoBlogBE.domain.user.UserAPIRequest;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<Long> registerUser(UserAPIRequest userAPIRequest);
    Mono<User> authorizeUser(String auth);
}
