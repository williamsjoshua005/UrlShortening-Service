package com.interswitch.assessment.urlshortening.controller;

import com.google.common.hash.Hashing;
import com.interswitch.assessment.urlshortening.model.Url;
import com.interswitch.assessment.urlshortening.service.UrlService;
import org.apache.commons.validator.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interswitch.assessment.urlshortening.model.Url;

import java.net.URI;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/rest/url")
public class UrlController {

    @Autowired
    UrlService urlService;
    @RequestMapping(value="/")
    public ResponseEntity<String> ping() throws Exception{
        return ResponseEntity.ok("pong - ");
    }

    @RequestMapping(value="/{url}")
    public ResponseEntity<Object> redirect(@PathVariable String url) throws Exception{
        String longUrl = urlService.getLongUrl(url);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(longUrl));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }
    @RequestMapping(value="/shorten")
    public ResponseEntity<Url> postTransaction(@RequestBody String longUrl) throws Exception{
        return ResponseEntity.ok(urlService.generateTinyUrl(longUrl));
    }

   /* @Autowired
    StringRedisTemplate redisTemplate;

    @GetMapping("/{id}")
    public String getUrl(@PathVariable String id){
        String url = redisTemplate.opsForValue().get(id);
        System.out.println("URL Retrieved: " + url);

        if(url == null){
            throw new RuntimeException("There is no shorter URL for : " + id);
        }

        return url;
    }

    @PostMapping
    public String Create(@RequestBody String url){
        UrlValidator urlValidator = new UrlValidator(
                new String[]{"http", "https"}
        );
        if(urlValidator.isValid(url)){
            String id = Hashing.murmur3_32().hashString(url, StandardCharsets.UTF_8).toString();

            System.out.println("URL generated: " + id);
            redisTemplate.opsForValue().set(id, url);
            return id;
        }
        throw new RuntimeException("Invalid URL" + url);
    }*/
}
