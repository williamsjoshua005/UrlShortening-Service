package com.interswitch.assessment.urlshortening.controller;


import com.interswitch.assessment.urlshortening.model.Url;
import com.interswitch.assessment.urlshortening.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import java.net.URI;


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


}
