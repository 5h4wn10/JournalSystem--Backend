package com.journalsystem.dto;

import com.journalsystem.model.Condition;

public class ConditionDTO {
    private Long id;
    private String conditionName;
    private String conditionDescription;
    private String practitionerName; // Endast namn på läkare

    public ConditionDTO(Condition condition) {
        this.id = condition.getId();
        this.conditionName = condition.getName();
        this.conditionDescription = condition.getDescription();
        this.practitionerName = condition.getPractitioner() != null ? condition.getPractitioner().getName() : "Unknown";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConditionName() {
        return conditionName;
    }

    public void setConditionName(String conditionName) {
        this.conditionName = conditionName;
    }

    public String getConditionDescription() {
        return conditionDescription;
    }

    public void setConditionDescription(String conditionDescription) {
        this.conditionDescription = conditionDescription;
    }

    public String getPractitionerName() {
        return practitionerName;
    }

    public void setPractitionerName(String practitionerName) {
        this.practitionerName = practitionerName;
    }
}
