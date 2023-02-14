package com.thierry.marcelin.bloggingapp.controllers;

import com.thierry.marcelin.bloggingapp.payload.dto.LoginDTO;
import com.thierry.marcelin.bloggingapp.payload.dto.RegisterDTO;
import com.thierry.marcelin.bloggingapp.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO){
        return new ResponseEntity<>(authService.login(loginDTO), HttpStatus.OK);
    }

    @PostMapping(value = {"/register", "signup"})
    public ResponseEntity<String> register(@Valid @RequestBody RegisterDTO registerDTO){
        return new ResponseEntity<>(authService.register(registerDTO), HttpStatus.CREATED);
    }
}
