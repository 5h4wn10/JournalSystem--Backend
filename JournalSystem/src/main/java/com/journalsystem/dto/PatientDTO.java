package com.journalsystem.dto;

import com.journalsystem.model.Role;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

public class PatientDTO {
    private Long id;
    private String name;
    private String address;
    private String personalNumber;
    private Date dateOfBirth;
    private Set<Role> roles;

    public PatientDTO(Long id, String name, String address, String personalNumber, Date dateOfBirth, Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.personalNumber = personalNumber;
        this.dateOfBirth = dateOfBirth;
        this.roles = roles;
    }

    // Getters and Setters
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
