package com.example.lorena.challengifier.models;

import java.util.UUID;

/**
 * Created by Lorena on 27.06.2017.
 */

public class MyChallenge {
    private UUID Id;
    private String Name;
    private String Description;
    private String Suggested_Time_UnitsId;
    private String User_Id;
    private int Suggested_Time_Number;
    private int Acceptance;

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getSuggested_Time_UnitsId() {
        return Suggested_Time_UnitsId;
    }

    public void setSuggested_Time_UnitsId(String suggested_Time_UnitsId) {
        Suggested_Time_UnitsId = suggested_Time_UnitsId;
    }

    public String getUser_Id() {
        return User_Id;
    }

    public void setUser_Id(String user_Id) {
        User_Id = user_Id;
    }

    public int getSuggested_Time_Number() {
        return Suggested_Time_Number;
    }

    public void setSuggested_Time_Number(int suggested_Time_Number) {
        Suggested_Time_Number = suggested_Time_Number;
    }

    public int getAcceptance() {
        return Acceptance;
    }

    public void setAcceptance(int acceptance) {
        Acceptance = acceptance;
    }
}
