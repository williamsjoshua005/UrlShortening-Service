package com.interswitch.assessment.urlshortening.util;

import javax.annotation.PostConstruct;
import com.interswitch.assessment.urlshortening.model.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ShortenUrlRepositoryImpl implements ShortenUrlRepository{

    private static final String KEY = "urlKey";

    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations hashOperations;

    @Autowired
    public ShortenUrlRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }


    @Override
    public void save(Url url) {
        hashOperations.put(KEY, url.getKey(), url);
    }


    @Override
    public Url findUrl(String key) {
        return (Url) hashOperations.get(KEY, key);
    }

}
