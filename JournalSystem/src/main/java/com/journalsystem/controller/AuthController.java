package com.journalsystem.controller;

import com.journalsystem.dto.AuthDTO;
import com.journalsystem.model.Role;
import com.journalsystem.model.User;
import com.journalsystem.service.AuthService;
import com.journalsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;



    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String registerUser(@RequestBody AuthDTO authRequest, @RequestParam Role role) {
        authService.registerUser(authRequest.getUsername(), authRequest.getPassword(), authRequest.getFullName(), role);
        return "Användare registrerad korrekt" + role;
    }

    /*@PostMapping("/login")
    public String loginUser(@RequestBody AuthDTO authRequest) {
        boolean isAuthenticated = authService.authenticate(authRequest.getUsername(), authRequest.getPassword());
        if (isAuthenticated) {
            return "Login successful!";
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }
    }*/



    @PostMapping("/login")
    public Map<String, Object> loginUser(@RequestBody AuthDTO authRequest) {
        boolean isAuthenticated = authService.authenticate(authRequest.getUsername(), authRequest.getPassword());
        if (isAuthenticated) {
            User user = userService.getUserByUsername(authRequest.getUsername())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

            Set<Role> roles = user.getRoles();
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("roles", roles);
            return response;
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }
    }



}
