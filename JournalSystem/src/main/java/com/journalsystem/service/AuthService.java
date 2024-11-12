package com.journalsystem.service;

import com.journalsystem.model.Patient;
import com.journalsystem.model.Practitioner;
import com.journalsystem.model.Role;
import com.journalsystem.model.User;
import com.journalsystem.repository.PatientRepository;
import com.journalsystem.repository.PractitionerRepository;
import com.journalsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PractitionerRepository practitionerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;  // Inject PasswordEncoder for secure password handling

    public boolean registerUser(String username, String password, String fullName, Role role) {
        if (userRepository.existsByUsername(username)) {
            System.out.println("Användarnamnet är redan upptaget.");
            return false;
        }

        User user = new User();
        user.setUsername(username);

        user.setFullName(fullName);
        user.setRoles(Collections.singleton(role));
        user.setPassword(password);

        userRepository.save(user);

        // Create associated Patient or Practitioner entity based on role
        if (role == Role.PATIENT) {
            Patient patient = new Patient();
            patient.setUser(user);
            patient.setName(user.getFullName());
            patientRepository.save(patient);
        }

        if (role == Role.DOCTOR || role == Role.STAFF) {
            Practitioner practitioner = new Practitioner();
            practitioner.setUser(user);
            practitioner.setName(user.getFullName());
            practitionerRepository.save(practitioner);
        }

        return true;
    }

    // Method for authenticating user, now using PasswordEncoder
    public boolean authenticate(String username, String password) {
        return userRepository.findByUsername(username)
                .map(user -> passwordEncoder.matches(password, user.getPassword()))  // Check encoded password
                .orElse(false);
    }
}
