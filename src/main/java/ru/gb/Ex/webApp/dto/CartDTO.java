package ru.gb.Ex.webApp.dto;

import lombok.Data;
import ru.gb.Ex.webApp.entities.Product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
public class CartDTO implements Serializable {


    private List<CartItemDTO> items;
    private int totalPrice;

    public CartDTO() {
        this.items = new ArrayList<>();
    }

    public void addProduct(Product product) {
        if (addProduct(product.getId())) {
            return;
        }
        items.add(new CartItemDTO(product));
        recalculate();
    }

    public boolean addProduct(Long id) {
        for (CartItemDTO o : items) {
            if (o.getId().equals(id)) {
                o.changeQuantity(1);
                recalculate();
                return true;
            }
        }
        return false;
    }

    public void decreaseProduct(Long id) {
        Iterator<CartItemDTO> iterator = items.iterator();
        while (iterator.hasNext()) {
            CartItemDTO next = iterator.next();
            if (next.getId().equals(id)) {
                next.changeQuantity(-1);
                if (next.getQuantity() <= 0) {
                    iterator.remove();
                }
                recalculate();
                return;
            }
        }
    }

    public void removeProduct(Long id) {
        items.removeIf(o -> o.getId().equals(id));
        recalculate();
    }

    public void clearCart(){
        items.clear();
        recalculate();
    }


    private void recalculate() {
        totalPrice = 0;
        for (CartItemDTO o : items) {
            totalPrice += o.getPrice();
        }
    }
}
