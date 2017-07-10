package com.example.lorena.challengifier.fragments.s;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.lorena.challengifier.R;
import com.example.lorena.challengifier.fragments.s.challenge.AddChallengeFragment;
import com.example.lorena.challengifier.fragments.s.challenge.ChallengeListFragment;
import com.example.lorena.challengifier.fragments.s.objective.AddObjectiveFragment;
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
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Main Menu");
        final Button button = (Button) view.findViewById(R.id.buttonViewChallenges);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FlowAids.IsMyObjectives = false;
                RxBus.get().post(ChallengeListFragment.SHOW_SCREEN,true);
            }
        });

        final Button buttonAddObjective = (Button) view.findViewById(R.id.buttonAddObjective);
        buttonAddObjective.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FlowAids.IsChallengeAccepted = false;
                RxBus.get().post(AddObjectiveFragment.SHOW_SCREEN,true);
            }
        });

        setDrawerState(true);


        final Button buttonAddChallenge = (Button) view.findViewById(R.id.AddChallengeID);
        buttonAddChallenge.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RxBus.get().post(AddChallengeFragment.SHOW_SCREEN,true);
            }
        });
        return view;
    }
}
