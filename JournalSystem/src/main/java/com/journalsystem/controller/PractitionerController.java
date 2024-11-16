package com.journalsystem.controller;

import com.journalsystem.model.Practitioner;
import com.journalsystem.dto.*;
import com.journalsystem.service.PractitionerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/practitioners")
public class PractitionerController {
    @Autowired
    private PractitionerService practitionerService;

    @PreAuthorize("hasAuthority('PATIENT')")
    @GetMapping
    public ResponseEntity<List<PractitionerDTO>> getAllPractitioners() {
        List<Practitioner> practitioners = practitionerService.getAllPractitioners();
        List<PractitionerDTO> practitionerDTOs = practitioners.stream()
                .map(practitioner -> new PractitionerDTO(
                        practitioner.getId(),
                        practitioner.getName(),
                        practitioner.getSpecialty(),
                        practitioner.getUser().getRoles()
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(practitionerDTOs);
    }

    @PostMapping
    public Practitioner addPractitioner(@RequestBody Practitioner practitioner) {
        return practitionerService.addPractitioner(practitioner);
    }

    @GetMapping("/{id}")
    public Practitioner getPractitionerById(@PathVariable Long id) {
        return practitionerService.getPractitionerById(id);
    }
}