package com.journalsystem.controller;

import com.journalsystem.model.Observation;
import com.journalsystem.model.Patient;
import com.journalsystem.service.AuthService;
import com.journalsystem.service.ObservationService;
import com.journalsystem.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@RestController
@RequestMapping("/api/observations")
public class ObservationController {

    @Autowired
    private ObservationService observationService;

    @Autowired
    private PatientService patientService;

    // Get all observations
    @GetMapping
    public List<Observation> getAllObservations() {
        return observationService.getAllObservations();
    }


    @PostMapping
    public Observation addObservation(@RequestBody Observation observation, Authentication authentication) {
        // Automatically associate with the currently authenticated practitioner
        return observationService.addObservationForPractitioner(observation, authentication);
    }

    // Add an observation for a specific patient
    @PreAuthorize("hasAuthority('DOCTOR')")
    @PostMapping("/patient/{patientId}")
    public Observation addObservationForPatient(@RequestBody Observation observation, @PathVariable Long patientId, Authentication authentication) {
        Patient patient = patientService.getPatientById(patientId);

        if (patient == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found");
        }

        // Save the observation for the patient, with the current practitioner as the observer
        return observationService.addObservationForPatient(observation, patient, authentication);
    }
    // Get a specific observation by ID
    @GetMapping("/{id}")
    public Observation getObservationById(@PathVariable Long id) {
        Observation observation = observationService.getObservationById(id);
        if (observation == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Observation not found");
        }
        return observation;
    }
}
