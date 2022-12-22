package ru.gb.ex.webapporders.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.ex.webapporders.dto.toMakeOrderDTO;
import ru.gb.ex.webapporders.service.OrderService;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/order")
    public void receiveOrder(toMakeOrderDTO toMakeOrderDTO) {
        orderService.makeOrder(toMakeOrderDTO);
    }

}
