package ru.gb.ex.webapporders.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.ex.webapporders.dto.CartItemDTO;
import ru.gb.ex.webapporders.dto.toMakeOrderDTO;
import ru.gb.ex.webapporders.entity.Order;
import ru.gb.ex.webapporders.entity.OrderItem;
import ru.gb.ex.webapporders.repository.OrderItemRepo;
import ru.gb.ex.webapporders.repository.OrderRepo;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderItemRepo itemRepo;

    private final OrderRepo orderRepo;

    @Transactional
    public void makeOrder(toMakeOrderDTO toMakeOrderDTO) {

        log.info("Incoming goods: " + toMakeOrderDTO.getCartItems());

        Order order = new Order();
        orderRepo.save(order);

        List<OrderItem> buffList = new ArrayList<>();

        for (CartItemDTO cartItem : toMakeOrderDTO.getCartItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setTitle(cartItem.getTitle());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPricePerProduct(cartItem.getPricePerProduct());
            orderItem.setPrice(cartItem.getPrice());
            orderItem.setOrder(order);
            buffList.add(orderItem);
           /* itemRepo.save(orderItem);*/
        }

        order.setOrderItems(buffList);
        order.setUserId(toMakeOrderDTO.getUserDTOreq().getId());
        order.setEmail(toMakeOrderDTO.getUserDTOreq().getEmail());
        int sum = buffList.stream().map(OrderItem::getPrice).reduce(0, Integer::sum);
        order.setTotal_price(sum);
    }




}
