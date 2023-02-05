package com.example.shorturl.controller;

import com.example.shorturl.dto.UrlLong;
import com.example.shorturl.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class UrlController {

    @Autowired
    private UrlService urlService;


    @PostMapping("/create")
    public String convertToShortUrl(@RequestBody UrlLong urlLong)
    {
        return urlService.convertToShortUrl(urlLong);
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Void> getAndRedirect(@PathVariable String shortUrl)
    {
        var url = urlService.getOriginalUrl(shortUrl);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(url))
                .build();
    }

}
