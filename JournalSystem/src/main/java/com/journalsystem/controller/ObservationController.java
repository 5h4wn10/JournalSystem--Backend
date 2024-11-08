package com.journalsystem.controller;

import com.journalsystem.model.Observation;
import com.journalsystem.model.Patient;
import com.journalsystem.model.Practitioner;
import com.journalsystem.repository.PractitionerRepository;
import com.journalsystem.service.ObservationService;
import com.journalsystem.service.PatientService;
import com.journalsystem.service.PractitionerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @Autowired
    private PractitionerService practitionerService;

    @GetMapping
    public List<Observation> getAllObservations() {
        return observationService.getAllObservations();
    }

    @PostMapping
    public Observation addObservation(@RequestBody Observation observation) {
        return observationService.addObservation(observation);
    }

    @PostMapping("/patient/{patientId}")
    public Observation addObservationForPatient(@RequestBody Observation observation, @PathVariable Long patientId) {
        Patient patient = patientService.getPatientById(patientId); // Hämta patienten med ID
        if (patient == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found");
        }

        return observationService.addObservationForPatient(observation, patient);
    }

    @GetMapping("/{id}")
    public Observation getObservationById(@PathVariable Long id) {
        return observationService.getObservationById(id);
    }
}