package com.example.lorena.challengifier.utils.session;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.lorena.challengifier.models.User;
import com.google.gson.Gson;

import static com.example.lorena.challengifier.activities.MainScreenActivity.updateDrawerContent;

/**
 * Created by Lorena on 18.01.2017.
 */

public class SessionUser {
    public static String currentUserId;
    public static String authToken;
    public static User loggedInUser;
    public static String key = "TokenKey";
    public static String keyUser = "UserKey";


    public static void saveSession(Activity activity, String tokenKey, User user) {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, tokenKey);
        editor.putString(keyUser, toGson(user));
        editor.commit();

        loggedInUser = user;
        authToken = tokenKey;
        currentUserId = user.getAspNetUserId();
    }

    public static void clearSession(Activity activity) {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.commit();
    }

    public static boolean isSessionStarted(Activity activity) {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        boolean isLoggedIn = !sharedPref.getString(key, "").isEmpty();
        if (isLoggedIn) {
            authToken = sharedPref.getString(key, "");
            loggedInUser = toUser(sharedPref.getString(keyUser, ""));
            updateDrawerContent();
        }
        return isLoggedIn;
    }

    private static String toGson(User user) {
        return new Gson().toJson(user);
    }

    private static User toUser(String gson) {
        return new Gson().fromJson(gson, User.class);
    }
}
