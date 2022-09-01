package ru.gb.Ex.webApp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import ru.gb.Ex.webApp.dto.CartItem;
import ru.gb.Ex.webApp.exceptions.ResourceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeleteProductService {

    private final CartService cartService;


    @CacheEvict(value = "items")
    public void deleteItem(Long id){
        List<CartItem> allItems = cartService.getAllItems();

        CartItem cartItem = allItems
                .stream()
                .filter(i -> i.getId().equals(id))
                .findAny()
                .orElseThrow(() -> new ResourceNotFoundException("Товар не найден!"));

        if (cartItem.getCount() > 1) {
            cartItem.setCount(cartItem.getCount() - 1);
            cartItem.setSum(cartItem.getPrice() * cartItem.getCount());
        } else allItems.remove(cartItem);

    }


}
