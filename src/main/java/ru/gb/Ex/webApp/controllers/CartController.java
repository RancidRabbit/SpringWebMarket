package ru.gb.Ex.webApp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.gb.Ex.webApp.dto.Cart;
import ru.gb.Ex.webApp.dto.CartItem;
import ru.gb.Ex.webApp.dto.ProductDTO;
import ru.gb.Ex.webApp.exceptions.ResourceNotFoundException;
import ru.gb.Ex.webApp.services.AddProductService;
import ru.gb.Ex.webApp.services.CartService;
import ru.gb.Ex.webApp.services.DeleteProductService;
import ru.gb.Ex.webApp.services.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    private final AddProductService addProduct;

    private final ProductService productService;

    private final DeleteProductService deleteProductService;

    @GetMapping("/cartCreate")
    public Cart getCart(@RequestParam Long userId){
        return cartService.createCart(userId);
    }

   @GetMapping("/cart")
   public List<CartItem> getAll(){
        return cartService.getAllItems();
   }

  @PostMapping("/cart/{id}")
  public void addToCart(@PathVariable int id) {
                 addProduct.addProduct(productService.findById(id)
                .map(ProductDTO::new)
                .orElseThrow(() -> new ResourceNotFoundException("Не найден продукт с id " + id)));
  }

  @DeleteMapping("/cart/{id}")
  public void deleteFromCart(@PathVariable int id){
        deleteProductService.deleteItem((long) id);
  }

}
