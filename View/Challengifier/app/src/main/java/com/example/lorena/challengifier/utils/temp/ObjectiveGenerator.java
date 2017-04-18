package com.example.lorena.challengifier.utils.temp;

import com.example.lorena.challengifier.models.Objective;
import com.example.lorena.challengifier.models.Objective;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Lorena on 11.01.2017.
 */

public class ObjectiveGenerator {
    static List<Objective> objectives;

    public static final List<Objective> getObjectives() {
        return objectives;
    }

    public static void setObjectives() throws ParseException {
        if(objectives == null || objectives.isEmpty()) {
            objectives = new ArrayList<>();

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

            Objective objectives1 = new Objective();
            objectives1.setId(UUID.randomUUID());
            objectives1.setDeadline(sdf.parse("12-04-2017"));
            objectives1.setDescription("In one evening.");
            objectives1.setStartDate(sdf.parse("12-04-2017"));
            objectives1.setEndDate(sdf.parse("12-04-2017"));
            objectives1.setExpectedOutcome("Expected Outcome");
            objectives1.setName("Run 1 mile");

            Objective objectives2 = new Objective();
            objectives2.setId(UUID.randomUUID());
            objectives2.setDeadline(sdf.parse("13-05-2017"));
            objectives2.setDescription("In two evenings.");
            objectives2.setStartDate(sdf.parse("12-04-2017"));
            objectives2.setEndDate(sdf.parse("12-04-2017"));
            objectives2.setExpectedOutcome("Expected Outcome");
            objectives2.setName("Run 1 mile");

            Objective objectives3 = new Objective();
            objectives3.setId(UUID.randomUUID());
            objectives3.setDeadline(sdf.parse("19-09-2017"));
            objectives3.setDescription("In three evenings.");
            objectives3.setStartDate(sdf.parse("12-04-2017"));
            objectives3.setEndDate(sdf.parse("12-04-2017"));
            objectives3.setExpectedOutcome("Expected Outcome");
            objectives3.setName("Run 1 mile");

            objectives.add(objectives1);
            objectives.add(objectives2);
            objectives.add(objectives3);
        }
    }

    public static void updateObjective(Objective objective){
        //objectives.
    }

    public static void addObjective(Objective objective){
        objectives.add(objective);
    }
}
