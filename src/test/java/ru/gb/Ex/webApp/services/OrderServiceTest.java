package ru.gb.Ex.webApp.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderServiceTest {


    @Autowired
    private TestRestTemplate testRestTemplate;


    @Test
    public void sendCartItemsTest(){



    }

}
