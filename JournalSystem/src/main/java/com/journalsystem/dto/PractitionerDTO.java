package com.journalsystem.dto;

import com.journalsystem.model.Role;

import java.util.Set;

public class PractitionerDTO {
    private Long id;
    private String name;
    private String specialty;
    private Set<Role> roles;

    public PractitionerDTO(Long id, String name, String specialty, Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
        this.roles = roles;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
