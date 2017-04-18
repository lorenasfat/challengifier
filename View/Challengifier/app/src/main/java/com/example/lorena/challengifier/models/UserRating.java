package com.example.lorena.challengifier.models;

import java.util.UUID;

/**
 * Created by Lorena on 08.01.2017.
 */

public class UserRating {
    private UUID id;
    private UUID userId;
    private UUID objectiveId;
    public int grade;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getObjectiveId() {
        return objectiveId;
    }

    public void setObjectiveId(UUID objectiveId) {
        this.objectiveId = objectiveId;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
