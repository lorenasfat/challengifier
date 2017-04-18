package com.example.lorena.challengifier.utils.temp;

import com.example.lorena.challengifier.models.PlanningStep;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Lorena on 11.01.2017.
 */

public class PlanningStepGenerator {
    static private List<PlanningStep> planningSteps;
    static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public static List<PlanningStep> getPlanningSteps() throws ParseException {

        planningSteps = new ArrayList<>();

        PlanningStep step1 = new PlanningStep();
        step1.setId(UUID.randomUUID());
        step1.setName("Step1");
        step1.setDescription("Description of Step 1.");
        step1.setStartDate(sdf.parse("12-04-2017"));
        step1.setEndDate(sdf.parse("12-05-2017"));

        PlanningStep step2 = new PlanningStep();
        step2.setId(UUID.randomUUID());
        step2.setName("Step2");
        step2.setDescription("Description of Step 2.");
        step2.setStartDate(sdf.parse("12-04-2017"));
        step2.setEndDate(sdf.parse("12-05-2017"));

        PlanningStep step3 = new PlanningStep();
        step3.setId(UUID.randomUUID());
        step3.setName("Step3");
        step3.setDescription("Description of Step 3.");
        step3.setStartDate(sdf.parse("12-04-2017"));
        step3.setEndDate(sdf.parse("12-05-2017"));

        planningSteps.add(step1);
        planningSteps.add(step2);
        planningSteps.add(step3);

        return planningSteps;
    }
}
