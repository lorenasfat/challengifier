package com.example.lorena.challengifier.models;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * Created by Lorena on 02.12.2016.
 */

public class Challenge implements Serializable {
    private UUID Id;
    private String Name;
    private String Description;
    private String Suggested_Time_UnitsId;
    private int Suggested_Time_Number;
    private List<PlanningStep> PlanningSteps;

    public List<PlanningStep> getPlanningSteps() {
        return PlanningSteps;
    }

    public void setPlanningSteps(List<PlanningStep> planningSteps) {
        this.PlanningSteps = planningSteps;
    }

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSuggested_Time_UnitsId() {
        return Suggested_Time_UnitsId;
    }

    public void setSuggested_Time_UnitsId(String suggested_Time_UnitsId) {
        Suggested_Time_UnitsId = suggested_Time_UnitsId;
    }

    public int getSuggested_Time_Number() {
        return Suggested_Time_Number;
    }

    public void setSuggested_Time_Number(int suggested_Time_Number) {
        Suggested_Time_Number = suggested_Time_Number;
    }
}
