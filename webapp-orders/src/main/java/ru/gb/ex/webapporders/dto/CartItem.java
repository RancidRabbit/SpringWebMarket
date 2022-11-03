package ru.gb.ex.webapporders.dto;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor

public class CartItem implements Serializable {

    private Long id;

    private String title;

    private Long count;

    private Double price;

    private Double sum;


}
