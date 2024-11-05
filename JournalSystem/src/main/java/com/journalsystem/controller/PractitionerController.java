package com.journalsystem.controller;

import com.journalsystem.model.Practitioner;
import com.journalsystem.service.PractitionerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/practitioners")
public class PractitionerController {
    @Autowired
    private PractitionerService practitionerService;

    @GetMapping
    public List<Practitioner> getAllPractitioners() {
        return practitionerService.getAllPractitioners();
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