package ru.gb.springsecurity.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.springsecurity.entities.Users;
import ru.gb.springsecurity.services.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public String homePage() {
        return "home page";
    }

    @GetMapping("/read")
    public String readOperation() {
        return "read something";
    }

    @GetMapping("/write")
    public String writeOperation(){
        return "write something";
    }

    @GetMapping("/delete")
    public String deleteOperation(){
        return "delete something";
    }


    @GetMapping("/info")
    public String user_info (Principal principal) {
        Users user = userService
                .findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("Пользователь " + principal.getName() + " не найден"));
        return user.getUsername() +  " " + user.getEmail();
    }

}
