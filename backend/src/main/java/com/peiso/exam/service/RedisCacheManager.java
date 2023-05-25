package com.peiso.exam.service;

import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

public interface RedisCacheManager<T> {

    void addToCache(String key, String value, int timeOut, TimeUnit timeUnit);

    Object fetchFromCache(String key);
}
