package ru.gb.ex.webapporders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.ex.webapporders.entity.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {






}
