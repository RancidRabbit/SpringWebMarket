package ru.gb.Ex.webApp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AuthRequest {

    private String username;
    private String password;

}
