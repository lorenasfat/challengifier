package com.example.lorena.challengifier.utils.communication;

import com.example.lorena.challengifier.models.ArchivedChallenge;
import com.example.lorena.challengifier.models.Challenge;
import com.example.lorena.challengifier.models.LeaderboardUser;
import com.example.lorena.challengifier.models.Milestone;
import com.example.lorena.challengifier.models.MyChallenge;
import com.example.lorena.challengifier.models.Objective;
import com.example.lorena.challengifier.models.ObjectiveForReviewDto;
import com.example.lorena.challengifier.models.ObjectiveHistory;

import java.util.List;

/**
 * Created by Lorena on 03.06.2017.
 */

public class FlowAids {
    public static Objective ObjectiveToEdit = null;
    public static Objective ObjectiveToView = null;
    public static ObjectiveForReviewDto ObjectiveToReview = null;

    public static Challenge ChallengeToEdit = null;
    public static Challenge ChallengeToView = null;
    public static MyChallenge MyChallengeToView = null;

    public static List<Objective> ObjectivesBackup = null;
    public static List<ObjectiveHistory> HistoryObjectivesBackup = null;
    public static List<Challenge> ChallengesBackup = null;
    public static List<MyChallenge> MyChallengesBackup = null;
    public static List<ArchivedChallenge> MyArchivedChallengesBackup = null;
    public static List<LeaderboardUser> LeaderboardUsersBackup = null;
    public static List<ObjectiveForReviewDto> ObjectivesForReviewBackup = null;

    public static boolean IsLinkChallengeToObjective = false;

    public static boolean IsMyObjectives = false;

    public static boolean IsChallengeAccepted = false;

    public static Milestone TempToBeAdded = null;
    public static boolean AccessToInternet = false;
    public static String BackUpTitle;

    public static void ClearCache(){
        ObjectiveToEdit = null;
        ObjectiveToView = null;
        ObjectiveToReview = null;

        ChallengeToEdit = null;
        ChallengeToView = null;
        MyChallengeToView = null;

        ObjectivesBackup = null;
        HistoryObjectivesBackup = null;
        ChallengesBackup = null;
        MyChallengesBackup = null;
        MyArchivedChallengesBackup = null;
        LeaderboardUsersBackup = null;
        ObjectivesForReviewBackup = null;

        IsLinkChallengeToObjective = false;
        IsMyObjectives = false;
        IsChallengeAccepted = false;
        TempToBeAdded = null;
        AccessToInternet = false;
    }
}
