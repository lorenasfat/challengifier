package com.example.lorena.challengifier.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lorena.challengifier.R;
import com.example.lorena.challengifier.activities.challenge.ChallengeListActivity;
import com.example.lorena.challengifier.activities.objective.AddObjectiveActivity;
import com.example.lorena.challengifier.activities.objective.ObjectiveListActivity;
import com.example.lorena.challengifier.activities.user.MyPerformancesActivity;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        final Button button = (Button) findViewById(R.id.buttonViewChallenges);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), ChallengeListActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        final Button buttonAddObjective = (Button) findViewById(R.id.buttonAddObjective);
        buttonAddObjective.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AddObjectiveActivity.class);
                startActivityForResult(intent,0);
            }
        });


        final Button buttonObjectiveList = (Button) findViewById(R.id.buttonViewObjectives);
        buttonObjectiveList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ObjectiveListActivity.class);
                startActivityForResult(intent,0);
            }
        });

        final Button buttonMyStats = (Button) findViewById(R.id.AddChallengeID);
        buttonMyStats.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MyPerformancesActivity.class);
                startActivityForResult(intent,0);
            }
        });
    }
}
