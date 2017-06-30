package com.example.lorena.challengifier.models;

import java.util.UUID;

/**
 * Created by Lorena on 08.01.2017.
 */

public class UserRating {
    private UUID Id;
    private String UserId;
    private UUID ObjectiveId;
    private int Grade;

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public UUID getObjectiveId() {
        return ObjectiveId;
    }

    public void setObjectiveId(UUID objectiveId) {
        ObjectiveId = objectiveId;
    }

    public int getGrade() {
        return Grade;
    }

    public void setGrade(int grade) {
        Grade = grade;
    }
}
