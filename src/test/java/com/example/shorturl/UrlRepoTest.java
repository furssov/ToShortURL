package com.example.shorturl;

import com.example.shorturl.entity.Url;
import com.example.shorturl.repos.UrlRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith({SpringExtension.class})
@DataJpaTest
public class UrlRepoTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UrlRepository urlRepository;


    @Test
    public void whenSavedThenCanFindByShortUrl()
    {
        var longUrl = "https://www.youtube.com/";
        var shortUrl = "www.youtube.com";
        var url = new Url();
        url.setLongUrl(longUrl);
        url.setShortUrl(shortUrl);

        urlRepository.save(url);
        var executed = urlRepository.findByShortUrl(shortUrl).get();
        Assertions.assertEquals(longUrl, executed.getLongUrl());
        Assertions.assertEquals(shortUrl, executed.getShortUrl());
        Assertions.assertNotEquals(executed.getShortUrl(), executed.getLongUrl());
    }

}
