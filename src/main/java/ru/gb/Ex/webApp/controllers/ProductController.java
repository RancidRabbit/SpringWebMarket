package ru.gb.Ex.webApp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.gb.Ex.webApp.dto.ProductDTO;
import ru.gb.Ex.webApp.entities.Categories;
import ru.gb.Ex.webApp.entities.Product;
import ru.gb.Ex.webApp.exceptions.ResourceNotFoundException;
import ru.gb.Ex.webApp.services.CategoryService;
import ru.gb.Ex.webApp.services.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    
    private final CategoryService categoryService;

    @GetMapping("/products")
    public Page<ProductDTO> findAll(@RequestParam(name = "p", defaultValue = "1") int pageIndex ) {
        if (pageIndex < 0) {
            pageIndex = 1;
        }
        return productService.findAll(pageIndex - 1, 10).map(ProductDTO::new);
    }

    @GetMapping("/products/{id}")
    public ProductDTO findById(@PathVariable int id) {
        return productService.findById(id)
                .map(ProductDTO::new)
                .orElseThrow(() -> new ResourceNotFoundException("Продукт с id: " + id + " не найден!"));
    }

    @GetMapping("/categories")
    public List<String> findAll(){
        return categoryService.findAllCategories();
    }

    @DeleteMapping("/products/delete/{id}")
    public void deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
    }


    @PutMapping("/products")
    public ProductDTO saveOrUpdate(@RequestBody ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setTitle(productDTO.getTitle());
        product.setPrice(productDTO.getPrice());
        Categories categories = categoryService
                .findByTitle(productDTO.getCategoryTitle())
                .orElseThrow(() -> new ResourceNotFoundException("Категория " + productDTO.getCategoryTitle() + " не существует!"));
        product.setCategories(categories);
        productService.addProduct(product);
        return new ProductDTO(product);
    }

    @PostMapping("/products")
    public ProductDTO addProduct(@RequestBody ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setTitle(productDTO.getTitle());
        product.setPrice(productDTO.getPrice());
        Categories categories = categoryService
                .findByTitle(productDTO.getCategoryTitle())
                .orElseThrow(() -> new ResourceNotFoundException("Категория " + productDTO.getCategoryTitle() + " не существует!"));
        product.setCategories(categories);
        productService.addProduct(product);
        return new ProductDTO(product);
    }

    @GetMapping("/products/filter")
    public List<Product> priceFilter(@RequestParam(value = "min", required = false) Integer min_price,
                                         @RequestParam(value = "max",required = false) Integer max_price) {
        if (Optional.ofNullable(max_price).isPresent() & Optional.ofNullable(min_price).isEmpty()) return productService.findProductByPriceBefore((max_price));
        if (Optional.ofNullable(min_price).isPresent() & Optional.ofNullable(max_price).isEmpty()) return productService.findProductByPriceAfter((min_price));
        return productService.findProductByPriceBetween((min_price),(max_price));
    }

}
