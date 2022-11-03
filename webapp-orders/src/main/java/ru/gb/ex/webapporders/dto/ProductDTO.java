package ru.gb.ex.webapporders.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDTO {


    private Long id;


    private String title;


    private int price;


    private String categoryTitle;


}
