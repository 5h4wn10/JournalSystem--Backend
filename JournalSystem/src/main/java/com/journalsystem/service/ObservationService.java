package com.journalsystem.service;

import com.journalsystem.model.Observation;
import com.journalsystem.model.Patient;
import com.journalsystem.model.Practitioner;
import com.journalsystem.model.User;
import com.journalsystem.repository.ObservationRepository;
import com.journalsystem.repository.PractitionerRepository;
import com.journalsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ObservationService {
    @Autowired
    private ObservationRepository observationRepository;

    @Autowired
    private PractitionerRepository practitionerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PractitionerService practitionerService;
    public List<Observation> getAllObservations() {
        return observationRepository.findAll();
    }

    public Observation addObservation(Observation observation) {
        return observationRepository.save(observation);
    }


    public Observation addObservationForPatient(Observation observation, Patient patient) {
        // Fetch the logged-in practitioner
        Practitioner practitioner = practitionerService.getLoggedInPractitioner();

        if (practitioner == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No authorized practitioner found");
        }

        // Associate the observation with the patient and the practitioner
        observation.setPatient(patient);
        observation.setPractitioner(practitioner);

        return observationRepository.save(observation);
    }

    public Observation getObservationById(Long id) {
        Optional<Observation> observation = observationRepository.findById(id);
        return observation.orElse(null);
    }

}