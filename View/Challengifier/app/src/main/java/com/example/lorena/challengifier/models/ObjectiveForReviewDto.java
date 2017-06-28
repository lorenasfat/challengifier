package com.example.lorena.challengifier.models;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Lorena on 28.06.2017.
 */

public class ObjectiveForReviewDto {
    private UUID Id;
    private String ObjectiveName;
    private String Description;
    private Date Deadline;
    private String ExpectedOutcome;
    private Date From;
    private Date To;
    private String Username;

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public String getObjectiveName() {
        return ObjectiveName;
    }

    public void setObjectiveName(String objectiveName) {
        ObjectiveName = objectiveName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Date getDeadline() {
        return Deadline;
    }

    public void setDeadline(Date deadline) {
        Deadline = deadline;
    }

    public String getExpectedOutcome() {
        return ExpectedOutcome;
    }

    public void setExpectedOutcome(String expectedOutcome) {
        ExpectedOutcome = expectedOutcome;
    }

    public Date getFrom() {
        return From;
    }

    public void setFrom(Date from) {
        From = from;
    }

    public Date getTo() {
        return To;
    }

    public void setTo(Date to) {
        To = to;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }
}
