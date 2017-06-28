package com.example.lorena.challengifier.utils.communication;

import com.example.lorena.challengifier.models.Challenge;
import com.example.lorena.challengifier.models.Milestone;
import com.example.lorena.challengifier.models.MyChallenge;
import com.example.lorena.challengifier.models.Objective;
import com.example.lorena.challengifier.models.ObjectiveForReviewDto;

import java.util.List;

/**
 * Created by Lorena on 03.06.2017.
 */

public class FlowAids {
    public static Objective ObjectiveToEdit = null;
    public static ObjectiveForReviewDto ObjectiveToReview = null;

    public static Challenge ChallengeToAddAsObjective = null;
    public static Challenge ChallengeToEdit = null;
    public static Challenge ChallengeToView = null;
    public static MyChallenge MyChallengeToView = null;

    public static List<Objective> ObjectivesBackup = null;
    public static List<Challenge> ChallengesBackup = null;
    public static List<MyChallenge> MyChallengesBackup = null;

    public static List<ObjectiveForReviewDto> ObjectivesForReviewBackup = null;

    public static boolean LinkChallengeToObjective = false;

    public static Milestone TempToBeAdded = null;
}
