package com.example.lorena.challengifier.activities.challenge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lorena.challengifier.R;
import com.example.lorena.challengifier.models.Challenge;
import com.example.lorena.challengifier.models.PlanningStep;
import com.example.lorena.challengifier.utils.adapters.PlanningStepListAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ViewChallengeActivity extends AppCompatActivity {
    PlanningStepListAdapter listAdapter;
    List<PlanningStep> planningSteps= new ArrayList<>();
    Activity activity;

    public ViewChallengeActivity() throws ParseException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_challenge);
        activity = this;

        Intent intent = getIntent();

        final Challenge challenge = (Challenge)intent.getSerializableExtra("ViewChallenge");

        TextView title = (TextView)findViewById(R.id.textViewChallengeTitle);
        title.setText(challenge.getName());

        TextView description = (TextView)findViewById(R.id.textViewChallengeDescription);
        description.setText(challenge.getDescription());

        TextView deadline = (TextView)findViewById(R.id.textViewChallengeDeadline);
        deadline.setText(new SimpleDateFormat("dd-MM-yyyy").format(challenge.getSuggested_Time_Number()));

        listAdapter = new PlanningStepListAdapter(this.getBaseContext());
        listAdapter.setPlanningSteps(planningSteps);

        final ListView list  = (ListView) findViewById(R.id.planningStepsList);
        list.setAdapter(listAdapter);
        registerForContextMenu(list);

        final Context context = this.getBaseContext();
        //buttonAddChallengeAsObjective
        Button button = (Button) findViewById(R.id.buttonAddChallengeAsObjective);
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Toast.makeText(context, "Challenge accepted!", Toast.LENGTH_LONG).show();
                //Intent intent = new Intent(activity, AddObjectiveActivity.class);
                //intent.putExtra("AcceptChallenge", challenge);
                //startActivity(intent);
            }
        });
    }
}
