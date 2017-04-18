package com.example.lorena.challengifier.models;

import java.util.UUID;

/**
 * Created by Lorena on 08.01.2017.
 */

public class Picture {
    private UUID id;
    private byte[] picture;
    private UUID challengeId;
    private UUID objectiveId;
    private UUID milestoneId;

    public UUID getMilestoneId() {
        return milestoneId;
    }

    public void setMilestoneId(UUID milestoneId) {
        this.milestoneId = milestoneId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public UUID getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(UUID challengeId) {
        this.challengeId = challengeId;
    }

    public UUID getObjectiveId() {
        return objectiveId;
    }

    public void setObjectiveId(UUID objectiveId) {
        this.objectiveId = objectiveId;
    }
}
