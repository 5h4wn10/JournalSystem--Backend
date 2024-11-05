package com.journalsystem.service;

import com.journalsystem.model.Organization;
import com.journalsystem.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OrganizationService {
    @Autowired
    private OrganizationRepository organizationRepository;

    public List<Organization> getAllOrganizations() {
        return organizationRepository.findAll();
    }

    public Organization addOrganization(Organization organization) {
        return organizationRepository.save(organization);
    }

    public Organization getOrganizationById(Long id) {
        Optional<Organization> organization = organizationRepository.findById(id);
        return organization.orElse(null);
    }
}