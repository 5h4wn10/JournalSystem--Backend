package com.journalsystem.repository;

import com.journalsystem.model.Patient;
import com.journalsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByUser(Optional<User> user);
}


