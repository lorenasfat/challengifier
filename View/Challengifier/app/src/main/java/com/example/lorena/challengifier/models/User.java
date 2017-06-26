package com.example.lorena.challengifier.models;

/**
 * Created by Lorena on 08.01.2017.
 */

public class User {
    private String AspNetUserId;
    private String Username;
    private int Points;

    public String getAspNetUserId() {
        return AspNetUserId;
    }

    public void setAspNetUserId(String aspNetUserId) {
        AspNetUserId = aspNetUserId;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public int getPoints() {
        return Points;
    }

    public void setPoints(int points) {
        Points = points;
    }
}
