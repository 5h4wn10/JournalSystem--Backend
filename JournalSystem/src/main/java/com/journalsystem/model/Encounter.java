package com.journalsystem.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Encounter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date encounterDate;
    private String type;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "practitioner_id")
    private Practitioner practitioner;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;


    public Encounter() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getEncounterDate() {
        return encounterDate;
    }

    public void setEncounterDate(Date encounterDate) {
        this.encounterDate = encounterDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Practitioner getPractitioner() {
        return practitioner;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setPractitioner(Practitioner practitioner) {
        this.practitioner = practitioner;
    }
}
