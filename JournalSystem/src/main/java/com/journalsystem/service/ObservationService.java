package com.journalsystem.service;

import com.journalsystem.model.Observation;
import com.journalsystem.repository.ObservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ObservationService {
    @Autowired
    private ObservationRepository observationRepository;

    public List<Observation> getAllObservations() {
        return observationRepository.findAll();
    }

    public Observation addObservation(Observation observation) {
        return observationRepository.save(observation);
    }

    public Observation getObservationById(Long id) {
        Optional<Observation> observation = observationRepository.findById(id);
        return observation.orElse(null);
    }
}