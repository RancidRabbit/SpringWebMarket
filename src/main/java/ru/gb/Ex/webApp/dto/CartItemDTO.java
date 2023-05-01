package ru.gb.Ex.webApp.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.Ex.webApp.entities.Product;

import java.io.Serializable;

@Data
@NoArgsConstructor

public class CartItemDTO implements Serializable {

    private Long id;

    private String title;

    private int quantity;

    private int pricePerProduct;

    private int price;

    public CartItemDTO(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.quantity = 1;
        this.pricePerProduct = product.getPrice();
        this.price = product.getPrice();
    }

   public void changeQuantity(int delta) {
        this.quantity += delta;
        this.price = this.quantity * this.pricePerProduct;
   }

}
