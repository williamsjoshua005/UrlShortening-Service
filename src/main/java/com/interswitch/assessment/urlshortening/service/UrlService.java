package com.interswitch.assessment.urlshortening.service;


import com.interswitch.assessment.urlshortening.model.Url;
import com.interswitch.assessment.urlshortening.util.ShortenUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UrlService {

    @Autowired
    public KeyGenerator keyGen;

    @Autowired
    public ShortenUrlRepository shortenUrlRepository;

    Map<String,Url> cache = new HashMap<>();

    public Url generateTinyUrl(String longUrl) {
        String key = keyGen.create();
        Url shortenUrl = new Url("http://localhost:8080/" + key, longUrl);

        shortenUrl.setKey(key);
        updateRedisCache(shortenUrl);

        return shortenUrl;
    }

    private void updateRedisCache(Url url) {
        shortenUrlRepository.save(url);
    }

    public String getLongUrl(String shortenUrl) {

        Url urlInstance = shortenUrlRepository.findUrl(shortenUrl);
        if (urlInstance != null)
            return urlInstance.getLongUrl();
        return "https://en.wikipedia.org/wiki/HTTP_404";
    }
}


