package ru.gb.Ex.webApp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.Ex.webApp.dto.ProductDTO;
import ru.gb.Ex.webApp.services.CartService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PutMapping("/cart/{id}")
    public void addProduct(@PathVariable int id) {
        cartService.addProduct(id);
    }

    @GetMapping("/cart")
    public List<ProductDTO> showAllProducts() {
        return cartService
                .showAllProducts()
                .stream()
                .map(ProductDTO::new)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/cart/{id}")
    public void deleteProduct(@PathVariable int id) {
        cartService.deleteProduct(id);
    }


}
