package com.yume24.rendevouz.token;

import com.yume24.rendevouz.jwt.JwtService;
import com.yume24.rendevouz.user.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final JwtService jwtService;

    public TokenDTO createTokens(UserDTO user) {
        var token = jwtService.createJwt(user.id(), Map.of("username", user.username()));
        return new TokenDTO(token);
    }
}
