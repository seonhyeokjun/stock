package com.example.stock.repository;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class RedisLockRepository {
    private RedisTemplate<String, String> redisTemplate;

    public RedisLockRepository(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Boolean lock(long key) {
        return redisTemplate
                .opsForValue()
                .setIfAbsent(generateKey(key), "lock", Duration.ofMillis(3_000));
    }

    public void unlock(long key) {
        redisTemplate.delete(generateKey(key));
    }

    private String generateKey(Long key) {
        return key.toString();
    }
}
