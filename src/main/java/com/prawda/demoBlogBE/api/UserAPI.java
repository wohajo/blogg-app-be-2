package com.prawda.demoBlogBE.api;

import am.ik.yavi.core.ConstraintViolation;
import com.prawda.demoBlogBE.domain.user.User;
import com.prawda.demoBlogBE.domain.user.UserAPIRequest;
import com.prawda.demoBlogBE.domain.user.UserAPIResponse;
import com.prawda.demoBlogBE.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class UserAPI {
    private final UserService userService;

    @CrossOrigin
    @PostMapping("/register")
    public Mono<Long> registerUser(@RequestBody @Valid UserAPIRequest userAPIRequest) {
        return
                UserAPIRequest
                        .validator()
                        .validateToEither(userAPIRequest)
                        .fold(
                                error -> Mono.error(new ResponseStatusException(
                                        HttpStatus.BAD_REQUEST,
                                        error
                                                .stream()
                                                .map(ConstraintViolation::message)
                                                .filter(s -> !s.contains("null"))
                                                .collect(Collectors.joining("||")))),
                                validatedUser -> userService.registerUser(validatedUser)
                        );
    }

    @CrossOrigin
    @PostMapping("/login")
    public Mono<UserAPIResponse> loginUser(@RequestHeader("Authorization") String auth) {
        return
                userService.authorizeUser(auth)
                .map(User::toAPIResponse);
    }
}
