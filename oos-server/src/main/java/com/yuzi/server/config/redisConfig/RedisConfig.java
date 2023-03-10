package com.yuzi.server.config.redisConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * redis配置类
 *
 * @author 星涯
 */
@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        //        String类型 key序列化器
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //        String类型 value序列化器
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        //        Hash类型 key序列化器
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        //        Hash类型 value序列化器
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }
}
