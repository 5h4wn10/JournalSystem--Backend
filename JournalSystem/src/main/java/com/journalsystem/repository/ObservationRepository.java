package com.journalsystem.repository;
import com.journalsystem.model.Observation;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ObservationRepository extends JpaRepository<Observation, Long> { }