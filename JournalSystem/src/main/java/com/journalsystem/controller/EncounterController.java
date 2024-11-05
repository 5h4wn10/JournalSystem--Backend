
package com.journalsystem.controller;

import com.journalsystem.model.Encounter;
import com.journalsystem.service.EncounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/encounters")
public class EncounterController {
    @Autowired
    private EncounterService encounterService;

    @GetMapping
    public List<Encounter> getAllEncounters() {
        return encounterService.getAllEncounters();
    }

    @PostMapping
    public Encounter addEncounter(@RequestBody Encounter encounter) {
        return encounterService.addEncounter(encounter);
    }

    @GetMapping("/{id}")
    public Encounter getEncounterById(@PathVariable Long id) {
        return encounterService.getEncounterById(id);
    }
}