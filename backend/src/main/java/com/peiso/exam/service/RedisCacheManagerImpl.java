package com.peiso.exam.service;

import com.peiso.exam.common.model.RedisUtil;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@AllArgsConstructor
public class RedisCacheManagerImpl implements RedisCacheManager{

    private RedisUtil<String> redisUtilString;
    @Override
    public void addToCache(String key, String value, int timeOut, TimeUnit timeUnit) {
        redisUtilString.putValue(key, value);
        redisUtilString.setExpire(key,timeOut, timeUnit);
    }

    @Override
    public Object fetchFromCache(String key) {
        return redisUtilString.getValue(key);
    }
}
