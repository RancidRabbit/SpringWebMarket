package ru.gb.ex.webapporders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.ex.webapporders.entity.OrderItem;

public interface OrderItemRepo extends JpaRepository <OrderItem, Long> {
}
