package ru.gb.ex.webapporders.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor

public class UserDTOreq {

    private Long id;

    private String username;

    private String email;


}
