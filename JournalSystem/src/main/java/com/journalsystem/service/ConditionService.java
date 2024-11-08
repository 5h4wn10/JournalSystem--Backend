package com.journalsystem.service;

import com.journalsystem.model.Condition;
import com.journalsystem.model.Patient;
import com.journalsystem.model.Practitioner;
import com.journalsystem.model.User;
import com.journalsystem.repository.ConditionRepository;
import com.journalsystem.repository.PractitionerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ConditionService {
    @Autowired
    private ConditionRepository conditionRepository;

    @Autowired
    private PractitionerRepository practitionerRepository;

    @Autowired
    private PractitionerService practitionerService;

    public List<Condition> getAllConditions() {
        return conditionRepository.findAll();
    }

    public Condition addCondition(Condition condition) {
        return conditionRepository.save(condition);
    }

    public Condition addConditionForPatient(Condition condition, Patient patient) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal(); // Hämta inloggad User
        Practitioner practitioner = practitionerService.getPractitionerByUserId(user.getId());

        if (practitioner == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Ingen behörighet");
        }

        condition.setPatient(patient);
        condition.setPractitioner(practitioner);
        return conditionRepository.save(condition);
    }


    /*private Practitioner getLoggedInPractitioner() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return practitionerRepository.findByUsername(username);
    }*/

    public Condition getConditionById(Long id) {
        Optional<Condition> condition = conditionRepository.findById(id);
        return condition.orElse(null);
    }
}