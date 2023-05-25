package com.peiso.exam.common.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Configuration
@Slf4j
public class RedisUtil<T> {

    /**
     * https://dzone.com/articles/implementation-of-redis-in-micro-servicespring-boo
     * https://markheath.net/post/exploring-redis-with-docker
     */
    private RedisTemplate<String, T> redisTemplate;
    private HashOperations<String, Object, T> hashOperation;
    private ListOperations<String, T> listOperation;
    private ValueOperations<String, T> valueOperations;

    @Autowired
    RedisUtil(final RedisTemplate<String, T> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperation = redisTemplate.opsForHash();
        this.listOperation = redisTemplate.opsForList();
        this.valueOperations = redisTemplate.opsForValue();
    }

    public void putMap(final String redisKey, final Object key, final T data) {
        hashOperation.put(redisKey, key, data);
    }

    public T getMapAsSingleEntry(final String redisKey, final Object key) {
        return hashOperation.get(redisKey, key);
    }

    public Map<Object, T> getMapAsAll(final String redisKey) {
        return hashOperation.entries(redisKey);
    }

    public void putValue(final String key, final T value) {
        valueOperations.set(key, value);
    }

    public Boolean hasKey(final String key) {
        log.info("key is: "+key);
        return redisTemplate.hasKey(key);
    }

    public void putValueWithExpireTime(final String key, final T value, final long timeout, final TimeUnit unit) {
        valueOperations.set(key, value, timeout, unit);
    }

    public T getValue(final String key) {
        return valueOperations.get(key);
    }

    public void setExpire(final String key, final long timeout, final TimeUnit unit) {
        redisTemplate.expire(key, timeout, unit);
    }
}
