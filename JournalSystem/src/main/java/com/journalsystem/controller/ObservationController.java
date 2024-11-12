package com.journalsystem.controller;

import com.journalsystem.dto.ObservationDTO;
import com.journalsystem.model.Observation;
import com.journalsystem.model.Patient;
import com.journalsystem.model.Practitioner;
import com.journalsystem.service.AuthService;
import com.journalsystem.service.ObservationService;
import com.journalsystem.service.PatientService;
import com.journalsystem.service.PractitionerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/observations")
public class ObservationController {

    @Autowired
    private ObservationService observationService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private PractitionerService practitionerService;

    // Get all observations
    @GetMapping
    public List<Observation> getAllObservations() {
        return observationService.getAllObservations();
    }

    // Add an observation for a specific patient
    @PostMapping
    public Observation addObservation(@RequestBody Map<String, Object> observationData) {
        Long patientId = Long.valueOf(observationData.get("patientId").toString());
        Long practitionerId = Long.valueOf(observationData.get("practitionerId").toString());

        Patient patient = patientService.getPatientById(patientId);
        Practitioner practitioner = practitionerService.getPractitionerById(practitionerId);

        if (patient == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found");
        }

        if (practitioner == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor not found");
        }

        // Create the observation and set details
        Observation observation = new Observation();
        observation.setDetails(observationData.get("details").toString());
        observation.setObservationDate(LocalDate.parse(observationData.get("observationDate").toString()));
        observation.setPatient(patient);
        observation.setPractitioner(practitioner);

        return observationService.addObservation(observation);
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
