package com.journalsystem.controller;

import com.journalsystem.model.Patient;
import com.journalsystem.model.User;
import com.journalsystem.service.PatientService;
import com.journalsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }


    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        Patient savedPatient = patientService.addPatient(patient);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPatient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePatient(@PathVariable Long id, @RequestBody Patient updatedPatient) {
        Optional<Patient> existingPatient = Optional.ofNullable(patientService.getPatientById(id));

        if (existingPatient.isPresent()) {
            Patient patient = existingPatient.get();
            // Uppdatera endast fält som tillhandahålls i updatedPatient
            if (updatedPatient.getAddress() != null) {
                patient.setAddress(updatedPatient.getAddress());
            }
            if (updatedPatient.getPersonalNumber() != null) {
                patient.setPersonalNumber(updatedPatient.getPersonalNumber());
            }


            patientService.addPatient(patient); // Spara den uppdaterade patienten
            return ResponseEntity.ok("Patientuppgifterna har uppdaterats.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patienten hittades inte.");
        }
    }

    @GetMapping("/me")
    public ResponseEntity<?> getPatientInfo() {
        // Get the authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Find the user by username
        Optional<User> user = userService.getUserByUsername(username);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        // Check if the user is a patient
        Optional<Patient> patient = patientService.findPatientByUser(user);
        if (patient == null) {
            return ResponseEntity.status(403).body("Access denied: User is not a patient.");
        }

        // Return the patient's own details
        return ResponseEntity.ok(patient);
    }

    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id);
    }
}
