package com.journalsystem.repository;

import com.journalsystem.model.Condition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConditionRepository extends JpaRepository<Condition, Long> {
}