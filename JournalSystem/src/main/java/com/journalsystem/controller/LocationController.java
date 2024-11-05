package com.journalsystem.controller;

import com.journalsystem.model.Location;
import com.journalsystem.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/locations")
public class LocationController {
    @Autowired
    private LocationService locationService;

    @GetMapping
    public List<Location> getAllLocations() {
        return locationService.getAllLocations();
    }

    @PostMapping
    public Location addLocation(@RequestBody Location location) {
        return locationService.addLocation(location);
    }

    @GetMapping("/{id}")
    public Location getLocationById(@PathVariable Long id) {
        return locationService.getLocationById(id);
    }
}