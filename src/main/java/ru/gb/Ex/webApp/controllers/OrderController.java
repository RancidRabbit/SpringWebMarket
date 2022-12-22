package ru.gb.Ex.webApp.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.Ex.webApp.services.OrderService;
import ru.gb.Ex.webApp.services.UserService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;

    @PostMapping("/send")
    /* Запилить Principal сюда? */
    public String sendCartItems(Authentication authentication) {
        if (authentication == null) {
            log.error("Principal is null!");
            throw new RuntimeException();
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        log.info("" + userDetails.getUsername());
        return  userDetails.getUsername();
    }


}
