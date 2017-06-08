package com.example.lorena.challengifier.fragments.s.challenge;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lorena.challengifier.R;

public class AddChallengeFragment extends Fragment {
    public static final String SHOW_SCREEN = "ADD_CHALLENGE_FRAGMENT_TAG";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_challenge, container, false);

        return view;
    }

}
