package ru.gb.Ex.webApp.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTOreq {

    private Long id;

    private String username;

    private String email;


}
