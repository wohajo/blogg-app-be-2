package com.prawda.demoBlogBE.api;

import com.prawda.demoBlogBE.domain.user.User;
import com.prawda.demoBlogBE.domain.user.UserAPIRequest;
import com.prawda.demoBlogBE.domain.user.UserAPIResponse;
import com.prawda.demoBlogBE.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class UserAPI {
    private final UserService userService;

    @PostMapping("/register")
    public Mono<Long> registerUser(@RequestBody UserAPIRequest userAPIRequest) {
        return
                userService.registerUser(userAPIRequest);
    }

    @PostMapping("/login")
    public Mono<UserAPIResponse> loginUser(@RequestHeader("Authorization") String auth) {
        return
                userService.authorizeUser(auth)
                .map(User::toAPIResponse);
    }
}
