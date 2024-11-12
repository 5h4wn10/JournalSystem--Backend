package com.journalsystem.controller;

import com.journalsystem.dto.AuthDTO;
import com.journalsystem.model.Role;
import com.journalsystem.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public String registerUser(@RequestBody AuthDTO authRequest, @RequestParam Role role) {
        authService.registerUser(authRequest.getUsername(), authRequest.getPassword(), authRequest.getFullName(), role);
        return "Användare registrerad korrekt" + role;
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody AuthDTO authRequest) {
        boolean isAuthenticated = authService.authenticate(authRequest.getUsername(), authRequest.getPassword());
        if (isAuthenticated) {
            return "Login successful!";
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }
    }



}
