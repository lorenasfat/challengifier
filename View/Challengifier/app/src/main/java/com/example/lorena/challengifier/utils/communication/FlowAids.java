package com.example.lorena.challengifier.utils.communication;

import com.example.lorena.challengifier.models.Challenge;
import com.example.lorena.challengifier.models.Milestone;
import com.example.lorena.challengifier.models.Objective;
import com.example.lorena.challengifier.models.PlanningStep;

import java.util.List;

/**
 * Created by Lorena on 03.06.2017.
 */

public class FlowAids {
    public static Objective ObjectiveToEdit = null;

    public static Challenge ChallengeToAddAsObjective = null;
    public static Challenge ChallengeToEdit = null;
    public static Challenge ChallengeToView = null;

    public static List<Objective> ObjectivesBackup = null;
    public static List<Challenge> ChallengesBackup = null;

    public static boolean LinkChallengeToObjective = false;

    public static Milestone TempToBeAdded = null;
}
