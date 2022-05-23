package com.fgsantana.datacaching.configuration;

import com.fgsantana.datacaching.domain.Game;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisConnectionFactory redisConnectionFactory(){
        return new LettuceConnectionFactory();
    }

    @Bean
    public RedisTemplate<String, Game> redisTemplate(){
        RedisTemplate<String, Game> redisTemplate = new RedisTemplate<>();

        redisTemplate.setConnectionFactory(redisConnectionFactory());

        //avoiding "\xac\xed\x00\x05" characters in hash key and hash value

        GenericToStringSerializer<Integer> genericToStringSerializer = new GenericToStringSerializer<>(Integer.class);
        redisTemplate.setKeySerializer(genericToStringSerializer);
        redisTemplate.setHashKeySerializer(genericToStringSerializer);


        Jackson2JsonRedisSerializer<Game> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Game.class);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);

        return redisTemplate;
    }
}
