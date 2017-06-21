package com.example.lorena.challengifier.fragments.s.user;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.lorena.challengifier.R;

/**
 * Created by Lorena on 09.05.2017.
 */

public class FrontScreenFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_front_screen, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Welcome");

        final Button button = (Button) view.findViewById(R.id.buttonLogin);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                getActivity().getSupportFragmentManager().beginTransaction()
                        .add(R.id.container, new LoginFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        final Button button2 = (Button) view.findViewById(R.id.btnSignUp);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                getActivity().getSupportFragmentManager().beginTransaction()
                        .add(R.id.container, new SignUpFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
        return view;
    }
}
