package com.example.lorena.challengifier.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Lorena on 02.12.2016.
 */

public class Challenge implements Serializable {
    private UUID id;
    private String description;
    private String title;
    private Date suggestedDeadline;
    private List<PlanningStep> planningSteps;

    public List<PlanningStep> getPlanningSteps() {
        return planningSteps;
    }

    public void setPlanningSteps(List<PlanningStep> planningSteps) {
        this.planningSteps = planningSteps;
    }

    public Date getSuggestedDeadline() {
        return suggestedDeadline;
    }

    public void setSuggestedDeadline(Date suggestedDeadline) {
        this.suggestedDeadline = suggestedDeadline;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
