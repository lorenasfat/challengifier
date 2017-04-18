package com.example.lorena.challengifier.models;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Lorena on 08.01.2017.
 */

public class PlanningStep implements Serializable {
    private UUID id;
    private Date startDate;
    private Date endDate;
    private String name;
    private String description;
    private UUID challengeId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(UUID challengeId) {
        this.challengeId = challengeId;
    }
}
