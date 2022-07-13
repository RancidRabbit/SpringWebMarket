package ru.gb.Ex.webApp.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.Ex.webApp.dto.AuthResponse;
import ru.gb.Ex.webApp.dto.UserDTO;
import ru.gb.Ex.webApp.entities.Role;
import ru.gb.Ex.webApp.entities.User;
import ru.gb.Ex.webApp.exceptions.DataValidationException;
import ru.gb.Ex.webApp.exceptions.ErrorMsg;
import ru.gb.Ex.webApp.exceptions.ResourceNotFoundException;
import ru.gb.Ex.webApp.repositories.RoleRepo;
import ru.gb.Ex.webApp.services.UserService;
import ru.gb.Ex.webApp.utils.JwtTokenUtil;

import javax.validation.Valid;
import java.util.Collections;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class RegisterController {

    private final UserService userService;

    private final RoleRepo roleRepo;

    private final BCryptPasswordEncoder passwordEncoder;

    private final JwtTokenUtil jwtTokenUtil;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> regUser (@RequestBody @Valid UserDTO userDTO, BindingResult result) {
        if (userService.findByUsername(userDTO.getUsername()).isEmpty()) {
            if (result.hasErrors()) {
                throw new DataValidationException(result
                        .getAllErrors()
                        .stream()
                        .map(ObjectError::getDefaultMessage)
                        .collect(Collectors.toList()));
            }
            Role role = roleRepo.findByName("ROLE_USER");
            User user = new User();
            user.setUsername(userDTO.getUsername());
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            if (userService.findByEmail(userDTO.getEmail()).isEmpty()) {
                user.setEmail(userDTO.getEmail());
            } else throw new ResourceNotFoundException("email " + userDTO.getEmail() + " уже используется");
            user.setRoles(Collections.singletonList(role));
            userService.saveUser(user);

        } else return new ResponseEntity<>(new ErrorMsg("Пользователь с ником " + userDTO.getUsername() + " уже существует!"), HttpStatus.BAD_REQUEST);
        UserDetails userDetails = userService.loadUserByUsername(userDTO.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponse(token));
    }


}
