package ru.gb.ex.webapporders.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
@Table(name = "order_items")
@EqualsAndHashCode(exclude = "order")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /* Насколько правильно оставлять генерацию ключей по дефолту postgre? */
    private Long id;

    @Column
    private String title;

    @Column
    private int quantity;

    @Column
    private int pricePerProduct;

    @Column
    private int price;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;


}
