package com.journalsystem.controller;

import com.journalsystem.model.Observation;
import com.journalsystem.service.ObservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/observations")
public class ObservationController {
    @Autowired
    private ObservationService observationService;

    @GetMapping
    public List<Observation> getAllObservations() {
        return observationService.getAllObservations();
    }

    @PostMapping
    public Observation addObservation(@RequestBody Observation observation) {
        return observationService.addObservation(observation);
    }

    @GetMapping("/{id}")
    public Observation getObservationById(@PathVariable Long id) {
        return observationService.getObservationById(id);
    }
}