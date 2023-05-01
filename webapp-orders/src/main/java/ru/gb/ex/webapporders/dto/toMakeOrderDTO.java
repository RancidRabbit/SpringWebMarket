package ru.gb.ex.webapporders.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/* Временное решение до настройки Spring Cloud */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class toMakeOrderDTO {

    private List<CartItemDTO> cartItems;
    private UserDTOreq userDTOreq;

}