package com.vedalingo.controllers;


import com.vedalingo.dtos.LoginUserDto;
import com.vedalingo.dtos.RegisterUserDto;
import com.vedalingo.exceptions.CustomAuthenticationException;
import com.vedalingo.models.User;
import com.vedalingo.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;


    // login user api
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginUserDto loginUserDto){
        System.out.println("User login successfully --");
        try {
            User user = authService.authenticateUser(loginUserDto);
            return ResponseEntity.ok().body(user.getFirstName());
        } catch ( CustomAuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed: " + e.getMessage());
        }
    }

    // register user  api
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterUserDto registerUserDto) {
        System.out.println("user registered successfully --");
        try {
            this.authService.registerUser(registerUserDto);
            return new ResponseEntity<>("User registered successfully !!", HttpStatus.CREATED);
        }
        catch ( CustomAuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed: " + e.getMessage());
        }
    }


}
