package com.journalsystem.controller;

import com.journalsystem.model.Patient;
import com.journalsystem.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }


    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        Patient savedPatient = patientService.addPatient(patient);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPatient);
    }

    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id);
    }
}
