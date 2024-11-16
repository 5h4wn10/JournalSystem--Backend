package com.journalsystem.service;

import com.journalsystem.dto.ConditionDTO;
import com.journalsystem.model.*;
import com.journalsystem.repository.ConditionRepository;
import com.journalsystem.repository.PractitionerRepository;
import com.journalsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConditionService {
    @Autowired
    private ConditionRepository conditionRepository;

    @Autowired
    private PractitionerRepository practitionerRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Condition> getAllConditions() {
        return conditionRepository.findAll();
    }

    public Condition addCondition(Condition condition) {
        return conditionRepository.save(condition);
    }

    public Condition addConditionForPatient(Condition condition, Patient patient, Authentication authentication) {
        condition.setPatient(patient);
        condition.setPractitioner(getAuthenticatedPractitioner(authentication));
        return conditionRepository.save(condition);
    }


    private Practitioner getAuthenticatedPractitioner(Authentication authentication) {
        String username = authentication.getName();

        // First, find the User by username
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authenticated user not found"));

        // Then, find the Practitioner by user ID
        return practitionerRepository.findByUserId(user.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authenticated user is not a practitioner"));
    }


    public Condition getConditionById(Long id) {
        Optional<Condition> condition = conditionRepository.findById(id);
        return condition.orElse(null);
    }


    public List<ConditionDTO> getConditionsByPatientId(Long patientId) {
        List<Condition> conditions = conditionRepository.findByPatientId(patientId);
        return conditions.stream()
                .map(ConditionDTO::new)  // Konvertera varje Condition till ConditionDTO
                .collect(Collectors.toList());
    }
}