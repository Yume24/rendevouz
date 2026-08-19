package com.yume24.rendevouz.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.Serializable;
import java.time.Instant;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JwtService {
    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.access.expiry}")
    private long expiry;

    private final JwtEncoder jwtEncoder;
    private final ReactiveJwtDecoder jwtDecoder;

    public String createJwt(String subject, Map<String, Object> claims) {
        var now = Instant.now();
        var claimsSet = JwtClaimsSet.builder().
                subject(subject)
                .issuer(issuer)
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .claims(claimMap -> claimMap.putAll(claims))
                .build();
        return jwtEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
    }

    public Mono<Jwt> decodeJwt(String jwt) {
        return jwtDecoder.decode(jwt);
    }
}
