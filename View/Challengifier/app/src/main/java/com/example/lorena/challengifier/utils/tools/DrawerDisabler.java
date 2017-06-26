package com.example.lorena.challengifier.utils.tools;

import android.support.v4.widget.DrawerLayout;

import static com.example.lorena.challengifier.activities.MainScreenActivity.mDrawerLayout;
import static com.example.lorena.challengifier.activities.MainScreenActivity.mDrawerToggle;

/**
 * Created by Lorena on 26.06.2017.
 */

public class DrawerDisabler {
    public static void setDrawerState(boolean isEnabled) {
        if ( isEnabled ) {
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            mDrawerToggle.onDrawerStateChanged(DrawerLayout.LOCK_MODE_UNLOCKED);
            mDrawerToggle.setDrawerIndicatorEnabled(true);
            mDrawerToggle.syncState();

        }
        else {
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            mDrawerToggle.onDrawerStateChanged(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            mDrawerToggle.setDrawerIndicatorEnabled(false);
            mDrawerToggle.syncState();
        }
    }
}
