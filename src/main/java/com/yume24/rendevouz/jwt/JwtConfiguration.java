package com.yume24.rendevouz.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@Configuration
public class JwtConfiguration {
    public static final String ROLE_CLAIM = "roles";
    public static final String ROLE_PREFIX = "ROLE_";
    private static final String JWT_ALGORITHM = "HMAC-SHA256";
    @Value("${jwt.secret}")
    private String secret;

    @Bean
    SecretKey secretKey() {
        return new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), JWT_ALGORITHM);
    }

    @Bean
    ReactiveJwtDecoder jwtDecoder(SecretKey secretKey) {
        return NimbusReactiveJwtDecoder.withSecretKey(secretKey).build();
    }

    @Bean
    JwtEncoder jwtEncoder(SecretKey secretKey) {
        return NimbusJwtEncoder.withSecretKey(secretKey).build();
    }

}
