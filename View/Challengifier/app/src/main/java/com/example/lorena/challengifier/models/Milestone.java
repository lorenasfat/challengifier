package com.example.lorena.challengifier.models;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Lorena on 08.01.2017.
 */

public class Milestone {

    public Milestone(){
        this.setId(UUID.randomUUID());
    }

    private UUID Id;
    private String Name;
    private String Description;
    private Date StartDate;
    private Date EndDate;
    private UUID ObjectiveId;
    private UUID PlanningStepId;

    public void setObjectiveId(UUID objectiveId) {
        this.ObjectiveId = objectiveId;
    }

    public UUID getPlanningStepId() {
        return PlanningStepId;
    }

    public void setPlanningStepId(UUID planningStepId) {
        this.PlanningStepId = planningStepId;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        this.Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public Date getStartDate() {
        return StartDate;
    }

    public void setStartDate(Date startDate) {
        this.StartDate = startDate;
    }

    public Date getEndDate() {
        return EndDate;
    }

    public void setEndDate(Date endDate) {
        this.EndDate = endDate;
    }

    public UUID getObjectiveId() {
        return ObjectiveId;
    }
}
