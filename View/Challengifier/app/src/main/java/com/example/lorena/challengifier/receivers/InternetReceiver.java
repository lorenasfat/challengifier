package com.example.lorena.challengifier.receivers;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.example.lorena.challengifier.activities.MainScreenActivity;
import com.hwangjr.rxbus.RxBus;

public class InternetReceiver extends BroadcastReceiver {
    public InternetReceiver() {
        super();
        RxBus.get().register(this);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        RxBus.get().unregister(this);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // an Intent broadcast.
        ConnectivityManager conMan = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMan.getActiveNetworkInfo();
        if (netInfo != null && netInfo.getType() == ConnectivityManager.TYPE_WIFI)
            RxBus.get().post(MainScreenActivity.YES_WIFI, true);
        else {
            RxBus.get().post(MainScreenActivity.NO_WIFI, true);
        }
    }

}
