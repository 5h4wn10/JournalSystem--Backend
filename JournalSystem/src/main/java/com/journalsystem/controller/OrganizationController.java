package com.journalsystem.controller;

import com.journalsystem.model.Organization;
import com.journalsystem.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/organizations")
public class OrganizationController {
    @Autowired
    private OrganizationService organizationService;

    @GetMapping
    public List<Organization> getAllOrganizations() {
        return organizationService.getAllOrganizations();
    }

    @PostMapping
    public Organization addOrganization(@RequestBody Organization organization) {
        return organizationService.addOrganization(organization);
    }

    @GetMapping("/{id}")
    public Organization getOrganizationById(@PathVariable Long id) {
        return organizationService.getOrganizationById(id);
    }
}