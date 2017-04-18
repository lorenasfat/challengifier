package com.example.lorena.challengifier.models;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Lorena on 08.01.2017.
 */

public class Milestone {
    private UUID id;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private UUID objectiveId;
    private UUID planningStepId;

    public void setObjectiveId(UUID objectiveId) {
        this.objectiveId = objectiveId;
    }

    public UUID getPlanningStepId() {
        return planningStepId;
    }

    public void setPlanningStepId(UUID planningStepId) {
        this.planningStepId = planningStepId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public UUID getObjectiveId() {
        return objectiveId;
    }
}
