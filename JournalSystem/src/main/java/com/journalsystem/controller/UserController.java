package com.journalsystem.controller;

import com.journalsystem.dto.UserDTO;
import com.journalsystem.model.User;
import com.journalsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/patients")
    public ResponseEntity<List<UserDTO>> getAllPatients() {
        List<UserDTO> patients = userService.getAllPatientsAsDTO();
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/practitioners")
    public ResponseEntity<List<UserDTO>> getAllPractitioners() {
        List<UserDTO> practitioners = userService.getAllPractitionersAsDTO();
        return ResponseEntity.ok(practitioners);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/receivers")
    public ResponseEntity<List<UserDTO>> getReceivers(Authentication authentication) {
        User currentUser = userService.findUserByUsername(authentication.getName());

        List<UserDTO> receivers;

        if (currentUser.hasRole("PATIENT")) {
            // Om användaren är patient, returnera alla practitioners
            List<User> practitioners = userService.getAllPractitioners(); // En metod som returnerar alla doctors och staff
            receivers = practitioners.stream().map(UserDTO::new).collect(Collectors.toList());
        } else if (currentUser.hasRole("DOCTOR") || currentUser.hasRole("STAFF")) {
            // Om användaren är practitioner, returnera alla patienter
            List<User> patients = userService.getAllPatients();
            receivers = patients.stream().map(UserDTO::new).collect(Collectors.toList());
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid user role");
        }

        return ResponseEntity.ok(receivers);
    }
}
