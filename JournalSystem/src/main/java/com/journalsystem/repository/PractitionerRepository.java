package com.journalsystem.repository;

import com.journalsystem.model.Practitioner;
import com.journalsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PractitionerRepository extends JpaRepository<Practitioner, Long> {
    Optional<Practitioner> findByUserId(Long userId);
}