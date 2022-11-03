package ru.gb.Ex.webApp.services;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.gb.Ex.webApp.dto.CartItem;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CartService cartService;
    private final RestTemplate restTemplate;
    private final String URL = "http://localhost:8070/app/order";
    /* Метод для передачи товаров из корзины в микросервис для обработки заказов */
    public void sendCartItems() {

        List<CartItem> allItems = cartService.getAllItems();

        restTemplate.postForEntity(URL, allItems, null);


    }



}
