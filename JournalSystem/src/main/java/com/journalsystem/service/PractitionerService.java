package com.journalsystem.service;

import com.journalsystem.model.Practitioner;
import com.journalsystem.model.User;
import com.journalsystem.repository.PractitionerRepository;
import com.journalsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PractitionerService {
    @Autowired
    private PractitionerRepository practitionerRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Practitioner> getAllPractitioners() {
        return practitionerRepository.findAll();
    }

    public Practitioner addPractitioner(Practitioner practitioner) {
        return practitionerRepository.save(practitioner);
    }

    public Practitioner getPractitionerById(Long id) {
        Optional<Practitioner> practitioner = practitionerRepository.findById(id);
        return practitioner.orElse(null);
    }

    public Practitioner getLoggedInPractitioner() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "No authenticated user found");
        }
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN, "User not found"));
        return practitionerRepository.findByUserId(user.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN, "Practitioner not found"));
    }


    public Practitioner getPractitionerByUserId(Long userId) {
        return practitionerRepository.findByUserId(userId).orElse(null);
    }
}
