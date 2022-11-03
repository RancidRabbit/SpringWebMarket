package ru.gb.ex.webapporders.controller;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.ex.webapporders.dto.CartItem;
import ru.gb.ex.webapporders.service.OrderService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/order")
    public void receiveOrder(@RequestBody List<CartItem> itemList) {
        orderService.makeOrder(itemList);
    }

}
