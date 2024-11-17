package com.journalsystem.service;

import com.journalsystem.model.User;
import com.journalsystem.dto.UserDTO;
import com.journalsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findUserByUsername(String username) {return userRepository.getByUsername(username);}


    public User createUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public boolean isPatient(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return user.hasRole("PATIENT");
    }

    public boolean isPractitioner(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return user.hasRole("DOCTOR") || user.hasRole("STAFF");
    }

    public List<User> getAllPatients() {
        return userRepository.findAll().stream()
                .filter(user -> user.hasRole("PATIENT"))
                .collect(Collectors.toList());
    }

    public List<User> getAllPractitioners() {
        return userRepository.findAll().stream()
                .filter(user -> user.hasRole("DOCTOR") || user.hasRole("STAFF"))
                .collect(Collectors.toList());
    }

    public UserDTO toDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getFullName(),
                user.getRoles()
        );
    }


    public List<UserDTO> getAllPatientsAsDTO() {
        return getAllPatients().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<UserDTO> getAllPractitionersAsDTO() {
        return getAllPractitioners().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

}
