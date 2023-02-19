package com.example.springurlshortener.configuration;

import com.example.springurlshortener.model.Url;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@RequiredArgsConstructor
public class RedisConfig {
    private final ObjectMapper mapper;
    private final RedisConnectionFactory connectionFactory;

    @Bean
    RedisTemplate<String, Url> redisTemplate() {

        final RedisTemplate<String, Url> redisTemplate = new RedisTemplate<>();

        var valueSerializer = new Jackson2JsonRedisSerializer<>(Url.class);
        valueSerializer.setObjectMapper(mapper);

        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(valueSerializer);

        return redisTemplate;
    }

}
