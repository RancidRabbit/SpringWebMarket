package ru.gb.Ex.webApp.services;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import ru.gb.Ex.webApp.dto.CartItem;
import ru.gb.Ex.webApp.dto.ProductDTO;
import ru.gb.Ex.webApp.exceptions.ResourceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddProductService {

    private final CartService cartService;

    private final CacheManager cacheManager;

    @CachePut(value = "items")
    public CartItem addProduct(ProductDTO productDTO) {

        List<CartItem> allItems = cartService.getAllItems();
        /* Если товар уже добавлен то повторное нажатие в корзину должно увеличить count */

        if (allItems.stream().anyMatch(i -> i.getId().equals(productDTO.getId()))) {
            CartItem cartItem = allItems
                    .stream()
                    .filter(i -> i.getId()
                            .equals(productDTO.getId()))
                    .findAny()
                    .orElseThrow(() -> new ResourceNotFoundException("Каточка товара не найднеа!"));

            cartItem.setCount(cartItem.getCount() + 1);
            cartItem.setSum(cartItem.getPrice() * cartItem.getCount());


           return cartItem;

        }

        CartItem item = CartItem.builder()
                .id(productDTO.getId())
                .title(productDTO.getTitle())
                .count(1L)
                .price((double) productDTO.getPrice())
                .sum((double) productDTO.getPrice() * 1)
                .build();

        allItems.add(item);
        return item;
    }


}




