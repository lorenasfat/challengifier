package com.example.lorena.challengifier.models;

import java.util.UUID;

/**
 * Created by Lorena on 10.07.2017.
 */

public class ArchivedChallenge {
    private UUID Id;
    private String Name;
    private String Description;
    private int AcceptedBy;

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

    public int getAcceptedBy() {
        return AcceptedBy;
    }

    public void setAcceptedBy(int acceptedBy) {
        AcceptedBy = acceptedBy;
    }
}
