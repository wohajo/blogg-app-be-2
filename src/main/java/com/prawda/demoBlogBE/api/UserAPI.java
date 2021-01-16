package com.prawda.demoBlogBE.api;

import com.prawda.demoBlogBE.domain.user.User;
import com.prawda.demoBlogBE.domain.user.UserAPIRequest;
import com.prawda.demoBlogBE.domain.user.UserAPIResponse;
import com.prawda.demoBlogBE.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class UserAPI {
    private final UserService userService;

    @CrossOrigin
    @PostMapping("/register")
    public Mono<Long> registerUser(@RequestBody @Valid UserAPIRequest userAPIRequest) {
        return
                userService.registerUser(userAPIRequest);
    }

    @CrossOrigin
    @PostMapping("/login")
    public Mono<UserAPIResponse> loginUser(@RequestHeader("Authorization") String auth) {
        return
                userService.authorizeUser(auth)
                .map(User::toAPIResponse);
    }
}
