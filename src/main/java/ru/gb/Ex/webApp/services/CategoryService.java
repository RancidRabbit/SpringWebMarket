package ru.gb.Ex.webApp.services;


import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.Ex.webApp.entities.Categories;
import ru.gb.Ex.webApp.repositories.CategoryRepo;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepo categoryRepo;

    public Optional<Categories> findByTitle(String title) {
        return categoryRepo.findByTitle(title);
    }

   public List<String> findAllCategories() {
        return categoryRepo.findAllCategories();
   }



}
