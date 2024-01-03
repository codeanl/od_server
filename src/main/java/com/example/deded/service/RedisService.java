package com.example.deded.service;

import org.springframework.data.redis.core.StringRedisTemplate;

public class RedisService {
    private final StringRedisTemplate redisTemplate;

    public RedisService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveToken(String userId, String token) {
        redisTemplate.opsForValue().set(userId, token);
    }

    public String getToken(String userId) {
        return redisTemplate.opsForValue().get(userId);
    }

    public void deleteToken(String userId) {
        redisTemplate.delete(userId);
    }
}
