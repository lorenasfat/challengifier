package com.example.lorena.challengifier.fragments.s.objective;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lorena.challengifier.R;
import com.example.lorena.challengifier.models.Challenge;
import com.example.lorena.challengifier.models.Objective;
import com.example.lorena.challengifier.services.api.services.ApiObjectiveService;
import com.example.lorena.challengifier.services.business.services.Validator;
import com.example.lorena.challengifier.services.external.services.retrofit.interfaces.ObjectiveService;
import com.example.lorena.challengifier.utils.communication.FlowAids;
import com.example.lorena.challengifier.utils.constants.ErrorMessages;
import com.example.lorena.challengifier.utils.constants.ObjectiveHelper;
import com.example.lorena.challengifier.utils.session.SessionUser;
import com.example.lorena.challengifier.utils.tools.DateFormatter;
import com.hwangjr.rxbus.RxBus;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddObjectiveFragment extends Fragment {
    public static final String SHOW_SCREEN = "ADD_OBJECTIVE_FRAGMENT_TAG";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_objective, container, false);

        //populate dropdown
        final Spinner dropdown = (Spinner) view.findViewById(R.id.spinnerAddObjectiveStatus);
        List<String> items = ObjectiveHelper.getStatusesForDisplay();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        Challenge challenge = FlowAids.ChallengeToAddAsObjective;

        final Objective objective = new Objective();

        final EditText editTextName = (EditText) view.findViewById(R.id.addObjectiveTextName);
        final EditText editTextDescription = (EditText) view.findViewById(R.id.addObjectiveDescription);
        final EditText editTextDeadline = (EditText) view.findViewById(R.id.addObjectiveDeadline);
        final EditText editTextExpectedOutcome = (EditText) view.findViewById(R.id.addObjectiveExpectedOutcome);
        if (challenge != null) {
            editTextName.setText(challenge.getName());
            editTextDescription.setText(challenge.getDescription());
        }

        Button save = (Button) view.findViewById(R.id.buttonAddObjective);
        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final String title = editTextName.getText().toString();
                final String description = editTextDescription.getText().toString();
                final String deadline = editTextDeadline.getText().toString();
                String expectedOutcome = editTextExpectedOutcome.getText().toString();
                int objectiveStatus = dropdown.getSelectedItemPosition();
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
                    objective.setName(title);
                    objective.setDeadline(deadlineDate);
                    objective.setExpectedOutcome(expectedOutcome);
                    objective.setDescription(description);
                    objective.setUserId(SessionUser.currentUser);
                    objective.setStatus(objectiveStatus);
                    ObjectiveService service = ApiObjectiveService.getService();
                    Call<Objective> call = service.addObjective(objective);
                    try {
                        call.enqueue(new Callback<Objective>() {
                            @Override
                            public void onResponse(Call<Objective> call, Response<Objective> response) {
                                Toast.makeText(getActivity().getApplicationContext(), "All set!", Toast.LENGTH_LONG).show();
                                RxBus.get().post(ObjectiveListFragment.SHOW_SCREEN, true);
                                // The network call was a success and we got a response
                            }

                            @Override
                            public void onFailure(Call<Objective> call, Throwable t) {
                                // the network call was a failure
                                // TODO: handle error
                                t.printStackTrace();
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        return view;
    }
}
