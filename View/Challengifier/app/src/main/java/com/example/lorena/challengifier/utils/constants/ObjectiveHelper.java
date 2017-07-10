package com.example.lorena.challengifier.utils.constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lorena on 14.01.2017.
 */

public class ObjectiveHelper {

    public static List<Status> Statuses;
    public static ArrayList<String> StatusNames;

    public static String getStatusName(int index) {
        if (Statuses == null) {
            populateStatusNames();
        }
        return StatusNames.get(index);
    }

    private static void populateStatusNames(){
        StatusNames = new ArrayList<>();
        StatusNames.add("Not started");
        StatusNames.add("Ongoing");
        StatusNames.add("Completed");
        StatusNames.add("Past deadline");
        StatusNames.add("To be reviewed");
        StatusNames.add("Reviewed");
    }

    private static void populateStatuses(){
        Statuses = new ArrayList<Status>();

        Status status1 = new Status(0, "not started");
        Status status2 = new Status(1, "ongoing");
        Status status3 = new Status(2, "completed");
        Status status4 = new Status(3, "past deadline");

        Statuses.add(status1);
        Statuses.add(status2);
        Statuses.add(status3);
        Statuses.add(status4);
    }

    public static List<String> getStatusesForDisplay() {
        if (Statuses == null) {
            populateStatuses();
        }
        List<String> statuses = new ArrayList<>();
        for (int i = 0; i < Statuses.size(); i++) {
            statuses.add(Statuses.get(i).display);
        }
        return statuses;
    }

    public static void setStatuses(List<Status> statuses) {
        Statuses = statuses;
    }

    private static class Status {
        private Status(int code, String display) {
            this.code = code;
            this.display = display;
        }

        private int code;
        private String display;
    }
}
