package com.example.readinglist;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebIntegrationTest(value={"server.port=0"})
public class SimpleWebTest {
    @Value("${local.server.port}")
    private int port;
    //@Test(expected=HttpClientErrorException.class)
    public void pageNotFound()
    {

        /*
        String url = "http://localhost:{port}/hello";
        try {
            RestTemplate rest = new RestTemplate();
            rest.getForObject(url,String.class,port);
            System.out.println("===========================hello");
            //fail("Should result in HTTP 404");
        }catch(HttpClientErrorException e)
        {
            //assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
            //throw e;
            System.out.println("===========================error");
        }*/
    }

}
