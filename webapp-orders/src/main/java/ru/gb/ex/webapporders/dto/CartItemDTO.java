package ru.gb.ex.webapporders.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor

public class CartItemDTO implements Serializable {

    private Long id;

    private String title;

    private int quantity;

    private int pricePerProduct;

    private int price;


}
