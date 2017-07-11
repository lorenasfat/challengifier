package com.example.lorena.challengifier.models;

import java.util.UUID;

/**
 * Created by Lorena on 11.07.2017.
 */

public class ObjectiveHistory {
    private UUID Id;
    private String Name;
    private String Description;
    private int Grade;
    private int Status;

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

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

    public int getGrade() {
        return Grade;
    }

    public void setGrade(int grade) {
        Grade = grade;
    }
}
