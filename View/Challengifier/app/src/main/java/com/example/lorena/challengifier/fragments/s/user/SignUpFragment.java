package com.example.lorena.challengifier.fragments.s.user;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.lorena.challengifier.R;
import com.example.lorena.challengifier.activities.MainMenuActivity;
import com.example.lorena.challengifier.services.api.services.ApiSignupService;
import com.example.lorena.challengifier.services.business.services.SignupService;

public class SignUpFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        boolean loggedIn = false;

        final EditText username = (EditText) view.findViewById(R.id.usernameField);
        final EditText password = (EditText) view.findViewById(R.id.passwordField);
        Button mEmailSignInButton = (Button) view.findViewById(R.id.buttonSignUp);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SignupService.signup(username.getText().toString(),password.getText().toString())){
                    Intent intent = new Intent(getActivity(), MainMenuActivity.class);
                    startActivityForResult(intent, 0);
                }
                else{
                    username.setError("Invalid credentials!");
                    username.requestFocus();
                    password.setError("Invalid credentials!");
                }
            }
        });

        return view;
    }
}
