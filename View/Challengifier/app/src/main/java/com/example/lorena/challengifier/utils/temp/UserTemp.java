package com.example.lorena.challengifier.utils.temp;

/**
 * Created by Lorena on 18.01.2017.
 */

public class UserTemp {
    public static String currentUser;

    public static String getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(String currentUser) {
        UserTemp.currentUser = currentUser;
    }
}
