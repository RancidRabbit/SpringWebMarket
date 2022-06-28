package ru.gb.Ex.webApp.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import ru.gb.Ex.webApp.entities.Product;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public class ProductDTO {


    private Long id;

    @NotNull(message = "Введите название предмета!")
    @Length(min = 1, max = 25, message = "Название предмета должно быть от 1 до 25 символов")
    @Pattern(regexp = "[а-яА-ЯЁё ]+", message = "Название должно состоять только из букв")
    private String title;

    @Min(value = 1, message = "Товар должен стоять минимум 1 монету")
    private int price;

    @NotNull(message = "Выберите категорию для предмета")
    private String categoryTitle;

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.categoryTitle = product.getCategories().getTitle();
    }
}
