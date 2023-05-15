package ru.gb.Ex.webApp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.Ex.webApp.dto.ProductDTO;
import ru.gb.Ex.webApp.entities.Categories;
import ru.gb.Ex.webApp.entities.Product;
import ru.gb.Ex.webApp.exceptions.ResourceNotFoundException;
import ru.gb.Ex.webApp.repositories.ProductRepo;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo repo;
    private final CategoryService categoryService;

    public Page<Product> findAll(int pageIndex, int pageSize) {
        return repo.findAll(PageRequest.of(pageIndex, pageSize));
    }

    public Optional<Product> findById(Long id) {
        return repo.findById(id);
    }

    public Product addProduct(Product product) {
        return repo.save(product);

    }

    public List<Product> findAll() {
        return repo.findAll(Sort.by("price").ascending());
    }

    public void deleteProduct(int id) {
        repo.deleteById((long) id);
    }

    @Transactional
    public void saveOrUpdate(ProductDTO productDTO) {
        Product product = findById(productDTO.getId()).orElseThrow(() ->
                new ResourceNotFoundException("Продукт с id " + productDTO.getId() + " не найден!"));
        product.setTitle(productDTO.getTitle());
        product.setPrice(productDTO.getPrice());
        Categories categories = categoryService
                .findByTitle(productDTO.getCategoryTitle())
                .orElseThrow(() -> new ResourceNotFoundException("Категория " + productDTO.getCategoryTitle() + " не существует!"));
        product.setCategories(categories);
    }


}

