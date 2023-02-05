package com.example.shorturl.service;

import com.example.shorturl.dto.UrlLong;
import com.example.shorturl.entity.Url;
import com.example.shorturl.repos.UrlRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlService {
    @Autowired
    private UrlRepository urlRepository;

    @Autowired
    private BaseConversion base;

    public String convertToShortUrl(UrlLong urlLong)
    {
        var url = new Url();
        url.setLongUrl(urlLong.getLongUrl());

        var entity = urlRepository.save(url);
        return base.encode(entity.getId());
    }

    public String getOriginalUrl(String shortUrl) {
        var id = base.decode(shortUrl);
        var entity = urlRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no entity with " + shortUrl));


        return entity.getLongUrl();
    }
}
