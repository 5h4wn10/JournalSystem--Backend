package com.journalsystem.service;

import com.journalsystem.model.Encounter;
import com.journalsystem.repository.EncounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EncounterService {
    @Autowired
    private EncounterRepository encounterRepository;

    public List<Encounter> getAllEncounters() {
        return encounterRepository.findAll();
    }

    public Encounter addEncounter(Encounter encounter) {
        return encounterRepository.save(encounter);
    }

    public Encounter getEncounterById(Long id) {
        Optional<Encounter> encounter = encounterRepository.findById(id);
        return encounter.orElse(null);
    }
}