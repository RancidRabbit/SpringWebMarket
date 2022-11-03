package ru.gb.ex.webapporders.service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.ex.webapporders.dto.CartItem;
import ru.gb.ex.webapporders.entity.Order;
import ru.gb.ex.webapporders.entity.OrderItem;
import ru.gb.ex.webapporders.repository.OrderItemRepo;
import ru.gb.ex.webapporders.repository.OrderRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderItemRepo itemRepo;

    private final OrderRepo orderRepo;

    @Transactional
    public void makeOrder(List<CartItem> cartItems) {

        System.out.println("Incoming goods: " + cartItems);

        Order order = new Order();
        orderRepo.save(order);
        List<OrderItem> buffList = new ArrayList<>();

        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setCount(cartItem.getCount());
            orderItem.setItem_price(cartItem.getSum());
            orderItem.setName(cartItem.getTitle());
            orderItem.setOrder(order);
            buffList.add(orderItem);
            itemRepo.save(orderItem);
        }

        order.setOrderItems(buffList);
        Double sum = buffList.stream().map(OrderItem::getItem_price).reduce(0., Double::sum);
        order.setPrice(sum);

    }




}
