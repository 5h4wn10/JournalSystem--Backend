package com.journalsystem.service;

import com.journalsystem.model.Condition;
import com.journalsystem.repository.ConditionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ConditionService {
    @Autowired
    private ConditionRepository conditionRepository;

    public List<Condition> getAllConditions() {
        return conditionRepository.findAll();
    }

    public Condition addCondition(Condition condition) {
        return conditionRepository.save(condition);
    }

    public Condition getConditionById(Long id) {
        Optional<Condition> condition = conditionRepository.findById(id);
        return condition.orElse(null);
    }
}