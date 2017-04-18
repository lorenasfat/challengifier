package com.example.lorena.challengifier.activities.objective;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lorena.challengifier.R;
import com.example.lorena.challengifier.models.Challenge;
import com.example.lorena.challengifier.models.Objective;

import java.text.SimpleDateFormat;

public class ViewObjectiveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_objective);

        TextView name = (TextView)findViewById(R.id.editTextObjectiveName);
        TextView description = (TextView)findViewById(R.id.editTextObjectiveDescription);
        TextView deadline = (TextView)findViewById(R.id.editTextObjectiveDeadline);
        TextView expectedOutcome = (TextView)findViewById(R.id.editTextObjectiveExpectedOutcome);
        TextView status = (TextView)findViewById(R.id.editTextViewObjectiveStatus);

        Intent intent = getIntent();

        final Objective objective = (Objective) intent.getSerializableExtra("ViewObjective");

        name.setText(objective.getName());
        description.setText(objective.getDescription());
        deadline.setText(new SimpleDateFormat("dd-MM-yyyy").format(objective.getDeadline()));
        expectedOutcome.setText(objective.getExpectedOutcome());
        status.setText(objective.getStatus());

        Button mark = (Button)findViewById(R.id.buttonMarkAsDone);
        mark.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //mark as completed
                Intent intent = new Intent(v.getContext(), ObjectiveListActivity.class);
                startActivityForResult(intent,0);
            }
        });
        Button edit = (Button)findViewById(R.id.buttonEditObjective);
        edit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AddObjectiveActivity.class);
                intent.putExtra("EditObjective", objective);
                startActivityForResult(intent,0);
            }
        });
    }
}
