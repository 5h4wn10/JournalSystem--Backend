package com.journalsystem.service;

import com.journalsystem.model.Location;
import com.journalsystem.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public Location addLocation(Location location) {
        return locationRepository.save(location);
    }

    public Location getLocationById(Long id) {
        Optional<Location> location = locationRepository.findById(id);
        return location.orElse(null);
    }
}