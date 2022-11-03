package ru.gb.ex.webapporders.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.gb.ex.webapporders.dto.CartItem;

import javax.persistence.*;


@Entity
@Getter
@Setter
@Table(name = "order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /* Насколько правильно оставлять генерацию ключей по дефолту postgre? */
    private Long id;

    @Column
    private Long count;

    @Column
    private String name;

    @Column
    private Double item_price;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;


}
