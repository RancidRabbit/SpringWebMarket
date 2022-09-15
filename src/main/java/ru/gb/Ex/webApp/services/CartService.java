package ru.gb.Ex.webApp.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.gb.Ex.webApp.dto.Cart;
import ru.gb.Ex.webApp.dto.CartItem;
import ru.gb.Ex.webApp.dto.ProductDTO;
import ru.gb.Ex.webApp.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartService {

    private final CacheManager manager;

    @Cacheable(value = "cart")
    public Cart createCart(Long userId) {
        Cart cart = new Cart();
        log.info("Creating cart instance");

        cart.setUserId(userId);

        return cart;
    }

    @Cacheable(value = "items")
    public List<CartItem> getAllItems() {
        Cart cart = createCart(1L);
        return cart.getItems();
    }







}
