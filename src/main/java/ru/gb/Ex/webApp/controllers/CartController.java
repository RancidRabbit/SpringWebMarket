package ru.gb.Ex.webApp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.Ex.webApp.dto.CartDTO;
import ru.gb.Ex.webApp.services.CartService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/carts")
public class CartController {

    private final CartService cartService;


    @GetMapping()
    public CartDTO getCurrentCart() {
        return cartService.getCurrentCart();
    }

    @GetMapping("/decrease/{id}")
    public void decreaseFromCart(@PathVariable Long id){
        cartService.decreaseFromCart(id);
    }

    @GetMapping("/add/{id}")
    public void addProductByIdToCart(@PathVariable Long id){
        cartService.addProductByIdToCart(id);
    }

   @GetMapping("/clear")
   public void clearCart(){
        cartService.clear();
   }



}
