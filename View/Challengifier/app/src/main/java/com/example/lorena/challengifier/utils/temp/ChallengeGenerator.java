package com.example.lorena.challengifier.utils.temp;

import com.example.lorena.challengifier.models.Challenge;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Lorena on 02.12.2016.
 */

public class ChallengeGenerator {
    static List<Challenge> challenges;

    public static List<Challenge> getChallenges() {
        return challenges;
    }

    public static void setChallenges() throws ParseException {
        if(challenges == null || challenges.isEmpty()) {
            challenges = new ArrayList<>();

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

            Challenge challenge1 = new Challenge();
            challenge1.setId(UUID.randomUUID());
            challenge1.setTitle("Walk 10 km");
            challenge1.setDescription("In one evening.");
            challenge1.setSuggestedDeadline(sdf.parse("12-04-2017"));
            challenge1.setPlanningSteps(PlanningStepGenerator.getPlanningSteps());

            Challenge challenge2 = new Challenge();
            challenge2.setTitle("Run 20 km");
            challenge2.setId(UUID.randomUUID());
            challenge2.setDescription("In a weekend");
            challenge2.setSuggestedDeadline(sdf.parse("22-05-2017"));
            challenge1.setPlanningSteps(PlanningStepGenerator.getPlanningSteps());

            Challenge challenge3 = new Challenge();
            challenge3.setTitle("Learn Ruby");
            challenge3.setId(UUID.randomUUID());
            challenge3.setDescription("Before next spring.");
            challenge3.setSuggestedDeadline(sdf.parse("02-03-2017"));
            challenge1.setPlanningSteps(PlanningStepGenerator.getPlanningSteps());

            challenges.add(challenge1);
            challenges.add(challenge2);
            challenges.add(challenge3);
        }
    }

    public static void addChallenge(Challenge challenge){
        challenges.add(challenge);
    }
}
