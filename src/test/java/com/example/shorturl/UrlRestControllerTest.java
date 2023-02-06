package com.example.shorturl;


import com.example.shorturl.controller.UrlController;
import com.example.shorturl.dto.UrlLong;
import com.example.shorturl.service.UrlService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({SpringExtension.class})
@WebMvcTest(UrlController.class)
public class UrlRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UrlService urlService;

    private static final String URL1 = "https://www.baeldung.com/spring-boot-testing";


    private static final String SHORT_URL1 = "www.baeldung.com";


    @BeforeEach
    public void setUp()
    {
        var url = new UrlLong();
        url.setLongUrl(URL1);
        Mockito.when(urlService.convertToShortUrl(url)).thenReturn(SHORT_URL1);
    }
    @Test
    public void controllerTest() throws Exception {
                    mockMvc.perform(post("/create")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                            .content("{\"longUrl\": \""+URL1+"\"}"))
                            .andExpect(status().isOk())
                            .andExpect(content().string(SHORT_URL1));

    }

}
