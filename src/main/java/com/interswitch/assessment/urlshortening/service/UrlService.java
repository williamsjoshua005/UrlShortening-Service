package com.interswitch.assessment.urlshortening.service;


import com.interswitch.assessment.urlshortening.model.Url;
import com.interswitch.assessment.urlshortening.util.ShortenUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlService {

    @Autowired
    public KeyGenerator keyGen;

    @Autowired
    public ShortenUrlRepository shortenUrlRepository;

    // Map<String,Url> cache = new HashMap<>();

    public Url generateTinyUrl(String longUrl) {
        String key = keyGen.create();
        Url shortenUrl = new Url("http://localhost:8080/" + key, longUrl);
        // cache.put(key, shortenUrl);
        shortenUrl.setKey(key);
        updateRedisCache(shortenUrl);
        // System.out.println(cache.get(key).getTinyUrl());
        return shortenUrl;
    }

    private void updateRedisCache(Url url) {
        shortenUrlRepository.save(url);
    }

    public String getLongUrl(String shortenUrl) {
        // System.out.println("get long url for - "+tinyUrl);
        // if(cache.containsKey(tinyUrl)) {
        // return cache.get(tinyUrl).getLongUrl();
        // }
        Url urlInstance = shortenUrlRepository.findUrl(shortenUrl);
        if (urlInstance != null)
            return urlInstance.getLongUrl();
        // System.out.println("URL Instance = "+urlInstance);
        // System.out.println("cache = "+cache);
        return "https://en.wikipedia.org/wiki/HTTP_404";
    }
}


