package com.journalsystem.service;

import com.journalsystem.model.Observation;
import com.journalsystem.model.Patient;
import com.journalsystem.model.Practitioner;
import com.journalsystem.model.User;
import com.journalsystem.repository.ObservationRepository;
import com.journalsystem.repository.PractitionerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
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
    private PractitionerService practitionerService;
    public List<Observation> getAllObservations() {
        return observationRepository.findAll();
    }

    public Observation addObservation(Observation observation) {
        return observationRepository.save(observation);
    }

   /* public Observation addObservationForPatient(Observation observation, Patient patient, Practitioner practitioner) {
        //Practitioner practitioner = getLoggedInPractitioner();
        if (practitioner == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Ingen behörighet");
        }
        observation.setPatient(patient);
        observation.setPractitioner(practitioner);
        return observationRepository.save(observation);
    }*/

    public Observation addObservationForPatient(Observation observation, Patient patient) {
        // Hämta autentisering från SecurityContext
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal(); // Hämta inloggade User
        // Hämta practitioner baserat på User-ID
        Practitioner practitioner = practitionerService.getPractitionerByUserId(user.getId());

        if (practitioner == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Ingen behörighet");
        }

        // Koppla patient och inloggad practitioner till observationen
        observation.setPatient(patient);
        observation.setPractitioner(practitioner);

        // Spara observationen
        return observationRepository.save(observation);
    }

    /*private Practitioner getLoggedInPractitioner() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return practitionerRepository.findByUsername(username);
    }*/

    public Observation getObservationById(Long id) {
        Optional<Observation> observation = observationRepository.findById(id);
        return observation.orElse(null);
    }
}