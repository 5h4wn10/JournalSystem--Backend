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
    private PasswordEncoder passwordEncoder;

    public void registerUser(String username, String password, String fullName, Role role) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setFullName(fullName);

        // Tilldela rollen direkt
        user.setRoles(Collections.singleton(role));

        userRepository.save(user);

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
    }

    /*public void registerUser(String username, String password, String fullName, Role role) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setFullName(fullName);
        user.setRoles(Set.of(role));

        userRepository.save(user);

        // Skapa en Patient-post om rollen är PATIENT
        if (role == Role.PATIENT) {
            Patient patient = new Patient();
            patient.setUser(user);
            patientRepository.save(patient);
        }
    }*/



}
