package com.yume24.rendevouz.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;

@Configuration
public class RedisConfiguration {
    @Bean
    ReactiveRedisOperations<String, String> redisOperations(ReactiveRedisConnectionFactory connectionFactory) {
        return new ReactiveStringRedisTemplate(connectionFactory);
    }
}
