package ru.gb.Ex.webApp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import ru.gb.Ex.webApp.dto.ProductDTO;
import ru.gb.Ex.webApp.entities.Categories;
import ru.gb.Ex.webApp.entities.Product;
import ru.gb.Ex.webApp.exceptions.DataValidationException;
import ru.gb.Ex.webApp.exceptions.ResourceNotFoundException;
import ru.gb.Ex.webApp.services.CategoryService;
import ru.gb.Ex.webApp.services.OrderService;
import ru.gb.Ex.webApp.services.ProductService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    private final CategoryService categoryService;

    private final OrderService orderService;

    @GetMapping("/products")
    public Page<ProductDTO> findAll(@RequestParam(name = "p", defaultValue = "1") int pageIndex) {
        if (pageIndex < 0) {
            pageIndex = 1;
        }
        return productService.findAll(pageIndex - 1, 10).map(ProductDTO::new);
    }

    @GetMapping("/products/{id}")
    public ProductDTO findById(@PathVariable Long id) {
        return productService.findById(id)
                .map(ProductDTO::new)
                .orElseThrow(() -> new ResourceNotFoundException("Продукт с id: " + id + " не найден!"));
    }

    @GetMapping("/categories")
    public List<String> findAll() {
        return categoryService.findAllCategories();
    }

    @DeleteMapping("/products/delete/{id}")
    public void deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
    }


    /**
     * @author Sumerkin A. 15.05.2023
     */
    @PutMapping("/products")
    public void saveOrUpdate(@RequestBody ProductDTO productDTO) {
        productService.saveOrUpdate(productDTO);
    }

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO addProduct(@RequestBody @Valid ProductDTO productDTO, BindingResult result) {
        if (result.hasErrors()) {
            throw new DataValidationException(result
                    .getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList()));
        }
        /* */
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


}
