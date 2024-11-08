package com.journalsystem.controller;

import com.journalsystem.model.Condition;
import com.journalsystem.model.Patient;
import com.journalsystem.service.ConditionService;
import com.journalsystem.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/conditions")
public class ConditionController {
    @Autowired
    private ConditionService conditionService;

    @Autowired
    private PatientService patientService;

    @GetMapping
    public List<Condition> getAllConditions() {
        return conditionService.getAllConditions();
    }

    @PostMapping
    public Condition addCondition(@RequestBody Condition condition) {
        return conditionService.addCondition(condition);
    }

    @PostMapping("/patient/{patientId}")
    public Condition addConditionForPatient(@RequestBody Condition condition, @PathVariable Long patientId) {
        Patient patient = patientService.getPatientById(patientId);
        if (patient == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found");
        }

        // Låt ConditionService hantera kopplingen till inloggad practitioner
        return conditionService.addConditionForPatient(condition, patient);
    }

    @GetMapping("/{id}")
    public Condition getConditionById(@PathVariable Long id) {
        return conditionService.getConditionById(id);
    }
}