package ru.gb.Ex.webApp.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.Ex.webApp.dto.CartDTO;
import ru.gb.Ex.webApp.dto.CartItemDTO;
import ru.gb.Ex.webApp.entities.Product;
import ru.gb.Ex.webApp.exceptions.ResourceNotFoundException;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ProductService productService;

    private CartDTO cartDTO;

    @PostConstruct
    public void init() {
        cartDTO = new CartDTO();
    }

    public CartDTO getCurrentCart(){
        int a = 5;
        return cartDTO;
    }

   public void decreaseFromCart(Long id) {
        cartDTO.decreaseProduct(id);
   }

   public List<CartItemDTO> getAllCartItems(){
       return cartDTO.getItems();
   }


   public void addProductByIdToCart(Long id) {
        if(!getCurrentCart().addProduct(id)) {
            Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Такого товара не существует!"));
            getCurrentCart().addProduct(product);
        }
   }

   public void clear(){
        getCurrentCart().clearCart();
   }


}