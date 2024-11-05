package com.journalsystem.service;

import com.journalsystem.model.Patient;
import com.journalsystem.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient getPatientById(Long id) {
        Optional<Patient> patient = patientRepository.findById(id);
        return patient.orElse(null);
    }
}