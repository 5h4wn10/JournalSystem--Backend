package com.journalsystem.service;

import com.journalsystem.model.Practitioner;
import com.journalsystem.repository.PractitionerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PractitionerService {
    @Autowired
    private PractitionerRepository practitionerRepository;

    public List<Practitioner> getAllPractitioners() {
        return practitionerRepository.findAll();
    }

    public Practitioner addPractitioner(Practitioner practitioner) {
        return practitionerRepository.save(practitioner);
    }

    public Practitioner getPractitionerById(Long id) {
        Optional<Practitioner> practitioner = practitionerRepository.findById(id);
        return practitioner.orElse(null);
    }
}
