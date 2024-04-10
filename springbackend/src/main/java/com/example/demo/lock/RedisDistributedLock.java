package com.example.demo.lock;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisDistributedLock {

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RedisDistributedLock(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public boolean acquireLock(String lockKey, long timeout, TimeUnit unit) {
        return redisTemplate.opsForValue().setIfAbsent(lockKey, "locked", timeout, unit);
    }

    public void releaseLock(String lockKey) {
        redisTemplate.delete(lockKey);
    }
}
