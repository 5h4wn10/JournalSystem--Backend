package com.journalsystem.repository;

import com.journalsystem.model.Practitioner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PractitionerRepository extends JpaRepository<Practitioner, Long> {
}