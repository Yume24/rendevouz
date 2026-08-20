package com.yume24.rendevouz.redis;

import com.yume24.rendevouz.user.UserLocationDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfiguration {
    public static final String KEY_DELIMITER = ":";

    @Bean
    ReactiveRedisOperations<String, Boolean> redisOperations(ReactiveRedisConnectionFactory connectionFactory) {
        var serializationContext = RedisSerializationContext.
                <String, Boolean>newSerializationContext(new GenericToStringSerializer<>(Boolean.class))
                .key(new StringRedisSerializer())
                .build();

        return new ReactiveRedisTemplate<>(connectionFactory, serializationContext);
    }

    @Bean
    ReactiveRedisOperations<String, UserLocationDTO> userLocationDTOReactiveRedisOperations(ReactiveRedisConnectionFactory connectionFactory) {
        var stringSerializer = new StringRedisSerializer();
        var userLocationSerializer = new JacksonJsonRedisSerializer<>(UserLocationDTO.class);

        var serializationContext = RedisSerializationContext
                .<String, UserLocationDTO>newSerializationContext()
                .key(stringSerializer)
                .value(userLocationSerializer)
                .hashKey(stringSerializer)
                .hashValue(userLocationSerializer)
                .build();

        return new ReactiveRedisTemplate<>(connectionFactory, serializationContext);
    }
}
