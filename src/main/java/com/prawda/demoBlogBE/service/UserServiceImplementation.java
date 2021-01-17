package com.prawda.demoBlogBE.service;

import com.prawda.demoBlogBE.domain.user.User;
import com.prawda.demoBlogBE.domain.user.UserAPIRequest;
import com.prawda.demoBlogBE.domain.user.UserDBO;
import com.prawda.demoBlogBE.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;


@Service
@AllArgsConstructor
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;

    @Override
    public Mono<Long> registerUser(UserAPIRequest userAPIRequest) {
        return userRepository
                .findByNameOrUsernameOrEmail(userAPIRequest.getName(), userAPIRequest.getUsername(), userAPIRequest.getEmail())
                .hasElements()
                .filter(aBoolean -> !aBoolean)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "This email, name or username is already in database.")))
                .thenReturn(userAPIRequest)
                .map(userAPIRequest1 -> userAPIRequest1.toDomain().toDBO())
                .flatMap(userRepository::save)
                .map(UserDBO::getId);
    }

    @Override
    public Mono<User> authorizeUser(String auth) {

        String[] authArray = auth.split("~");

        return Mono
                .just(authArray)
                .filter(strings -> strings.length == 2)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Authorization header should be formatted: [username]~[password].")))
                .flatMap(authArray1 -> userRepository
                        .findByUsernameAndPasswordHash(authArray1[0], DigestUtils.sha512Hex(authArray1[1])))
                .map(userDBO -> userDBO.toDomain())
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not authorized.")));
    }
}
