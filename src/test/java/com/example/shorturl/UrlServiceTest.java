package com.example.shorturl;

import com.example.shorturl.dto.UrlLong;
import com.example.shorturl.entity.Url;
import com.example.shorturl.repos.UrlRepository;
import com.example.shorturl.service.UrlService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith({SpringExtension.class})
public class UrlServiceTest {

    @TestConfiguration
    static class UrlServiceTestConfig {
        @Bean
        public UrlService urlService()
        {
            return new UrlService();
        }
    }

    @Autowired
    private UrlService urlService;

    @MockBean
    private UrlRepository urlRepository;

    private static final String URL1 = "https://www.baeldung.com/spring-boot-testing";
    private static final String URL2 = "https://www.youtube.com/";
    private static final String URL3 = "https://github.com/";

    private static final String SHORT_URL1 = "www.baeldung.com";
    private static final String SHORT_URL2 = "www.youtube.com";
    private static final String SHORT_URL3 = "github.com";



    @BeforeEach
    public void setUp()
    {
        Url url1 = new Url();
        url1.setLongUrl(URL1);
        url1.setShortUrl(SHORT_URL1);
        Mockito.when(urlRepository.save(url1)).thenReturn(url1);

        Url url2 = new Url();
        url2.setLongUrl(URL2);
        url2.setShortUrl(SHORT_URL2);
        Mockito.when(urlRepository.save(url2)).thenReturn(url2);

        Url url3 = new Url();
        url3.setLongUrl(URL3);
        url3.setShortUrl(SHORT_URL3);
        Mockito.when(urlRepository.save(url3)).thenReturn(url3);

        Mockito.when(urlRepository.findByShortUrl(SHORT_URL1)).thenReturn(Optional.of(url1));
        Mockito.when(urlRepository.findByShortUrl(SHORT_URL2)).thenReturn(Optional.of(url2));
        Mockito.when(urlRepository.findByShortUrl(SHORT_URL3)).thenReturn(Optional.of(url3));

    }
    @Test
    public void convertToShortUrl()
    {
        var longUrl1 = new UrlLong();
                longUrl1.setLongUrl(URL1);
        var longUrl2 = new UrlLong();
                longUrl2.setLongUrl(URL2);
        var longUrl3 = new UrlLong();
                longUrl3.setLongUrl(URL3);



        Assertions.assertEquals(SHORT_URL1, urlService.convertToShortUrl(longUrl1));
        Assertions.assertEquals(SHORT_URL2, urlService.convertToShortUrl(longUrl2));
        Assertions.assertEquals(SHORT_URL3, urlService.convertToShortUrl(longUrl3));


    }

    @Test
    public void getOriginalUrl()
    {
        Assertions.assertEquals(URL1, urlService.getOriginalUrl(SHORT_URL1));
        Assertions.assertEquals(URL2, urlService.getOriginalUrl(SHORT_URL2));
        Assertions.assertEquals(URL3, urlService.getOriginalUrl(SHORT_URL3));
    }
}
