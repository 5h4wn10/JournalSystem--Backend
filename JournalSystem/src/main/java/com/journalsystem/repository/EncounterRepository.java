package com.journalsystem.repository;

import com.journalsystem.model.Encounter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EncounterRepository extends JpaRepository<Encounter, Long> {
}