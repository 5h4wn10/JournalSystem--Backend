package com.journalsystem.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Practitioner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String specialty;



    @OneToMany(mappedBy = "practitioner")
    private List<Observation> observations;

    @OneToMany(mappedBy = "practitioner")
    private List<Encounter> encounters;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    //@JoinColumn(name = "id")  // Detta gör att user_id används som primärnyckel
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
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

    public List<Observation> getObservations() {
        return observations;
    }

    public void setObservations(List<Observation> observations) {
        this.observations = observations;
    }

    public List<Encounter> getEncounters() {
        return encounters;
    }

    public void setEncounters(List<Encounter> encounters) {
        this.encounters = encounters;
    }
}
