package com.example.lorena.challengifier.models;

import java.util.UUID;

/**
 * Created by Lorena on 08.01.2017.
 */

public class UserRank {
    private UUID id;
    private UUID userId;
    private double systemGrate;
    private double usersGrade;
    private double challengerGrade;
    private double finalGrade;

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

    public double getSystemGrate() {
        return systemGrate;
    }

    public void setSystemGrate(double systemGrate) {
        this.systemGrate = systemGrate;
    }

    public double getUsersGrade() {
        return usersGrade;
    }

    public void setUsersGrade(double usersGrade) {
        this.usersGrade = usersGrade;
    }

    public double getChallengerGrade() {
        return challengerGrade;
    }

    public void setChallengerGrade(double challengerGrade) {
        this.challengerGrade = challengerGrade;
    }

    public double getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(double finalGrade) {
        this.finalGrade = finalGrade;
    }
}
