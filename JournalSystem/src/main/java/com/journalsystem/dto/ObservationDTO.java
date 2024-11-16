package com.journalsystem.dto;

import com.journalsystem.model.Observation;

import java.util.Date;

public class ObservationDTO {
    private Long id;
    private String details;
    private Date observationDate;
    private String practitionerName; // Endast namn på läkare

    // Konstruktor för att mappa från Observation-entity
    public ObservationDTO(Observation observation) {
        this.id = observation.getId();
        this.details = observation.getDetails();
        this.observationDate = observation.getObservationDate();
        this.practitionerName = observation.getPractitioner() != null ? observation.getPractitioner().getName() : "Unknown";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getObservationDate() {
        return observationDate;
    }

    public void setObservationDate(Date observationDate) {
        this.observationDate = observationDate;
    }

    public String getPractitionerName() {
        return practitionerName;
    }

    public void setPractitionerName(String practitionerName) {
        this.practitionerName = practitionerName;
    }
}
