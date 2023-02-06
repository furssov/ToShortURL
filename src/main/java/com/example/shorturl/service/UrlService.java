package com.example.shorturl.service;

import com.example.shorturl.dto.UrlLong;
import com.example.shorturl.entity.Url;
import com.example.shorturl.repos.UrlRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UrlService {
    @Autowired
    private UrlRepository urlRepository;

    public String convertToShortUrl(UrlLong urlLong)
    {
        var url = new Url();
        var longUrl= urlLong.getLongUrl();

        Pattern pattern = Pattern.compile("http[s*]://[a-z]+\\.[a-z]+\\.*[a-z]*/*.*");
        Matcher matcher = pattern.matcher(longUrl);

        if (matcher.matches())
        {
            pattern = Pattern.compile("[a-z]+\\.[a-z]+\\.*[a-z]*");
            matcher = pattern.matcher(longUrl);
            matcher.find();
            var shortUrl = longUrl.substring(matcher.start(), matcher.end());
            url.setLongUrl(longUrl);
            url.setShortUrl(shortUrl);
            var executed = urlRepository.save(url);
            return executed.getShortUrl();
        }
        else throw new RuntimeException("bad url");


    }

    public String getOriginalUrl(String shortUrl)
    {
        return urlRepository.findByShortUrl(shortUrl).get().getLongUrl();
    }


}
