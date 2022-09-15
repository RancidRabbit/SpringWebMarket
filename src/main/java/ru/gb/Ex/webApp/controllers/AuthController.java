package ru.gb.Ex.webApp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.Ex.webApp.dto.AuthRequest;
import ru.gb.Ex.webApp.dto.AuthResponse;
import ru.gb.Ex.webApp.exceptions.ErrorMsg;
import ru.gb.Ex.webApp.services.UserService;
import ru.gb.Ex.webApp.utils.JwtTokenUtil;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    private final JwtTokenUtil jwtTokenUtil;

    private final AuthenticationManager manager;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody AuthRequest authRequest) {
        try {
            manager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new ErrorMsg("Incorrect username or password"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponse(token));
    }

}
