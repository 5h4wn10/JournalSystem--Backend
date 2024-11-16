package com.journalsystem.controller;

import com.journalsystem.dto.ConditionDTO;
import com.journalsystem.model.Condition;
import com.journalsystem.model.Patient;
import com.journalsystem.model.Practitioner;
import com.journalsystem.service.ConditionService;
import com.journalsystem.service.PatientService;
import com.journalsystem.service.PractitionerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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
    public List<ConditionDTO> getAllConditions() {
        return conditionService.getAllConditions().stream().map(ConditionDTO::new).toList();
    }

    @PostMapping
    public Condition addCondition(@RequestBody Condition condition) {
        return conditionService.addCondition(condition);
    }



    @PreAuthorize("hasAnyAuthority('DOCTOR', 'STAFF')")
    @PostMapping("/patient/{patientId}")
    public Condition addConditionForPatient(@RequestBody Condition condition, @PathVariable Long patientId, Authentication authentication) {
        Patient patient = patientService.getPatientById(patientId);

        if (patient == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found");
        }

        return conditionService.addConditionForPatient(condition, patient, authentication);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConditionDTO> getConditionById(@PathVariable Long id) {
        Condition condition = conditionService.getConditionById(id);
        if (condition == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Condition not found");
        }
        return ResponseEntity.ok(new ConditionDTO(condition));
    }



    @PreAuthorize("hasAnyAuthority('DOCTOR', 'PATIENT', 'STAFF')")
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<ConditionDTO>> getConditionsByPatientId(@PathVariable Long patientId) {
        List<ConditionDTO> conditions = conditionService.getConditionsByPatientId(patientId);
        return ResponseEntity.ok(conditions);
    }
}