package ru.gb.Ex.webApp.services;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.Ex.webApp.dto.ProductDTO;
import ru.gb.Ex.webApp.entities.Product;
import ru.gb.Ex.webApp.exceptions.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class CartService {

    private final ProductService productService;

    private List<Product> products = new ArrayList<>();


    public void addProduct(int id) {

        Product product = productService
                .findById(id)
                .get();

        products.add(product);


    }

    public List<Product> showAllProducts() {
        return products;
    }


    public void deleteProduct(int id) {

        Product product = productService
                .findById(id)
                .get();

        products.remove(product);
    }


}
