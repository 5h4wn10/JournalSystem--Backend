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

    public Observation addObservationForPractitioner(Observation observation, Authentication authentication) {
        Practitioner practitioner = getAuthenticatedPractitioner(authentication);
        observation.setPractitioner(practitioner);
        return observationRepository.save(observation);
    }


    public Observation addObservationForPatient(Observation observation, Patient patient, Authentication authentication) {
        Practitioner practitioner = getAuthenticatedPractitioner(authentication);
        observation.setPatient(patient);
        observation.setPractitioner(practitioner);
        return observationRepository.save(observation);
    }

    private Practitioner getAuthenticatedPractitioner(Authentication authentication) {
        String username = authentication.getName();

        // First, find the User by username
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authenticated user not found"));

        // Then, find the Practitioner by user ID
        return practitionerRepository.findByUserId(user.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authenticated user is not a practitioner"));
    }

    public Observation getObservationById(Long id) {
        return observationRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Observation not found"));
    }

}