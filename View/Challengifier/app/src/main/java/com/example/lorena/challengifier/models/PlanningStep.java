package com.example.lorena.challengifier.models;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Lorena on 08.01.2017.
 */

public class PlanningStep implements Serializable {
    private UUID Id;
    private String Name;
    private String Description;
    private UUID ChallengeId;
    private String TimeUnitId;
    private int TimeUnitNumber;

    public String getTimeUnitId() {
        return TimeUnitId;
    }

    public void setTimeUnitId(String timeUnitId) {
        TimeUnitId = timeUnitId;
    }

    public int getTimeUnitNumber() {
        return TimeUnitNumber;
    }

    public void setTimeUnitNumber(int timeUnitNumber) {
        TimeUnitNumber = timeUnitNumber;
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

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public UUID getChallengeId() {
        return ChallengeId;
    }

    public void setChallengeId(UUID challengeId) {
        this.ChallengeId = challengeId;
    }
}
