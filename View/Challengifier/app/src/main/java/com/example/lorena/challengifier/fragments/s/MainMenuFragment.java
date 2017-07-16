package com.example.lorena.challengifier.fragments.s;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.lorena.challengifier.R;
import com.example.lorena.challengifier.fragments.s.challenge.AddChallengeFragment;
import com.example.lorena.challengifier.fragments.s.challenge.ArchivedChallengeListFragment;
import com.example.lorena.challengifier.fragments.s.challenge.ChallengeListFragment;
import com.example.lorena.challengifier.fragments.s.objective.AddObjectiveFragment;
import com.example.lorena.challengifier.fragments.s.objective.ObjectiveHistoryListFragment;
import com.example.lorena.challengifier.utils.communication.FlowAids;
import com.hwangjr.rxbus.RxBus;

import static com.example.lorena.challengifier.utils.tools.DrawerDisabler.setDrawerState;

public class MainMenuFragment extends Fragment {
    public static final String SHOW_SCREEN = "MAIN_FRAGMENT_MENU_TAG";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_menu, container, false);

        //if (isConnected(getActivity().getApplicationContext())) {

            ((AppCompatActivity) getActivity()).getSupportActionBar().show();
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Main Menu");
            FlowAids.BackUpTitle = "Main Menu";
            final Button button = (Button) view.findViewById(R.id.buttonViewChallenges);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    FlowAids.IsMyObjectives = false;
                    RxBus.get().post(ChallengeListFragment.SHOW_SCREEN, true);
                }
            });

            final Button buttonAddObjective = (Button) view.findViewById(R.id.buttonAddObjective);
            buttonAddObjective.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    FlowAids.IsChallengeAccepted = false;
                    RxBus.get().post(AddObjectiveFragment.SHOW_SCREEN, true);
                }
            });

            setDrawerState(true);

            ImageView archive = (ImageView) view.findViewById(R.id.archive);
            archive.setClickable(true);
            archive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RxBus.get().post(ArchivedChallengeListFragment.SHOW_SCREEN, true);
                }
            });

            ImageView history = (ImageView) view.findViewById(R.id.history);
            history.setClickable(true);
            history.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RxBus.get().post(ObjectiveHistoryListFragment.SHOW_SCREEN, true);
                }
            });

            final Button buttonAddChallenge = (Button) view.findViewById(R.id.AddChallengeID);
            buttonAddChallenge.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    RxBus.get().post(AddChallengeFragment.SHOW_SCREEN, true);
                }
            });
       // }
        return view;
    }

    public boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        if (activeNetwork != null) {
            boolean isWifi = activeNetwork.getType() == ConnectivityManager.TYPE_WIFI;
            boolean isMobile = activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE;

            if ((isWifi || isMobile) && activeNetwork.isConnected()) {
                FlowAids.AccessToInternet = true;
                return true;
            } else {
                showDialog();
                return false;
            }
        } else {
            showDialog();
            return false;
        }
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Turn mobile data on or come back later")
                .setCancelable(false)
                .setPositiveButton("Turn mobile data on", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        startActivity(new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS));
                    }
                })
                .setNegativeButton("Quit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        getActivity().finish();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

}
