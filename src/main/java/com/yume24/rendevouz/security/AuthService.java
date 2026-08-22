package com.yume24.rendevouz.security;

import com.yume24.rendevouz.jwt.JwtService;
import com.yume24.rendevouz.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final JwtService jwtService;

    public Mono<TokensDTO> anonymousLogin(String username) {
        return userService.createAnonymousUser(username)
                .map(user -> jwtService.createJwt(user.id(), Optional.empty()))
                .map(TokensDTO::new);
    }
}
