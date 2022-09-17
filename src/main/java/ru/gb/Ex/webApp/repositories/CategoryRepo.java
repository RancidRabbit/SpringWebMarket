package ru.gb.Ex.webApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.gb.Ex.webApp.dto.ProductDTO;
import ru.gb.Ex.webApp.entities.Categories;
import ru.gb.Ex.webApp.entities.Product;

import java.util.List;
import java.util.Optional;

public interface CategoryRepo extends JpaRepository<Categories, Long> {

      Optional<Categories> findByTitle(String title);

      @Query("select title from Categories ")
      List<String> findAllCategories();

}
