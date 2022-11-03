package ru.gb.Ex.webApp.dto;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@Builder

public class CartItem implements Serializable {

    private Long id;

    private String title;

    private Long count;

    private Double price;

    private Double sum;


}
