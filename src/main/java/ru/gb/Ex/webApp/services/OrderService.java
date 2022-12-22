package ru.gb.Ex.webApp.services;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.gb.Ex.webApp.converter.UserConverter;
import ru.gb.Ex.webApp.dto.CartItemDTO;
import ru.gb.Ex.webApp.dto.UserDTOreq;
import ru.gb.Ex.webApp.dto.toMakeOrderDTO;
import ru.gb.Ex.webApp.entities.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CartService cartService;
    private final RestTemplate restTemplate;
    private final UserConverter converter;
    private final String URL = "http://localhost:8070/app/order";
    /* Метод для передачи товаров из корзины в микросервис для обработки заказов */
    public ResponseEntity<?> sendCartItems(User user) {
        /* Такое решение с передачей сюда юзера временное до настройки Spring Cloud что бы привязать заказы к
        * определенному юзеру в БД */
        List<CartItemDTO> allItems = cartService.getAllCartItems();
        UserDTOreq userDTOreq = converter.entityToDto(user);
        restTemplate.postForEntity(URL, new toMakeOrderDTO(allItems,userDTOreq),null);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
