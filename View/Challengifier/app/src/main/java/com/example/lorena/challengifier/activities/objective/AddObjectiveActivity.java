package com.example.lorena.challengifier.activities.objective;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lorena.challengifier.R;
import com.example.lorena.challengifier.models.Challenge;
import com.example.lorena.challengifier.models.Objective;
import com.example.lorena.challengifier.services.business.services.Validator;
import com.example.lorena.challengifier.utils.constants.ErrorMessages;
import com.example.lorena.challengifier.utils.constants.ObjectiveHelper;
import com.example.lorena.challengifier.utils.tools.DateFormatter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddObjectiveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_objective);
        Intent intent = getIntent();

        //can be prepopulated when accepting a challenge
        final Challenge challenge = (Challenge) intent.getSerializableExtra("AcceptChallenge");

        //populate dropdown
        Spinner dropdown = (Spinner) findViewById(R.id.spinnerObjectiveStatus);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,
                ObjectiveHelper.getStatusesForDisplay());
        dropdown.setAdapter(adapter);
        final Context context = this.getBaseContext();


        Objective objective = new Objective();

        final EditText editTextName = (EditText) findViewById(R.id.editTextName);
        final EditText editTextDescription = (EditText) findViewById(R.id.editTextDescription);
        final EditText editTextDeadline = (EditText) findViewById(R.id.editTextDeadline);
        final EditText editTextExpectedOutcome = (EditText) findViewById(R.id.editTextExpectedOutcome);
        final Spinner spinnerObjectiveStatus = (Spinner) findViewById(R.id.spinnerObjectiveStatus);
        if (challenge != null) {
            editTextName.setText(challenge.getName());
            editTextDescription.setText(challenge.getDescription());
            editTextDeadline.setText(new SimpleDateFormat("dd-MM-yyyy").format(challenge.getSuggested_Time_Number()));
        }

        Button save = (Button) findViewById(R.id.buttonEditObjective);
        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final String title = editTextName.getText().toString();
                final String description = editTextDescription.getText().toString();
                final String deadline = editTextDeadline.getText().toString();
                String expectedOutcome = editTextExpectedOutcome.getText().toString();
                String objectiveStatus = spinnerObjectiveStatus.getSelectedItem().toString();
                Date deadlineDate = new Date();

                boolean ok = true;
                if (Validator.isEmpty(title)) {
                    editTextName.setError(ErrorMessages.EMPTY);
                    ok = false;
                }
                if (Validator.isEmpty(description)) {
                    editTextDescription.setError(ErrorMessages.EMPTY);
                    ok = false;
                }
                if (Validator.isEmpty(deadline)) {
                    editTextDeadline.setError(ErrorMessages.EMPTY);
                    ok = false;
                }
                if (!Validator.isDate(deadline)) {
                    editTextDeadline.setError(ErrorMessages.WRONG_DATE_FORMAT);
                    ok = false;
                } else {
                    deadlineDate = DateFormatter.getDate(deadline);
                }
                if (ok == true) {
                    Objective objective = new Objective();
                    objective.setName(title);
                    objective.setDeadline(deadlineDate);
                    objective.setExpectedOutcome(expectedOutcome);
                    objective.setDescription(description);
                    if(challenge!=null)
                        objective.setChallengeId(challenge.getId());

                    /*try {
                        ObjectiveGenerator.setObjectives();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    ObjectiveGenerator.addObjective(objective);
*/
                    Toast.makeText(v.getContext(), "All set!", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(v.getContext(), ObjectiveListActivity.class);
                    startActivityForResult(intent, 0);
                }
            }
        });
    }
}
