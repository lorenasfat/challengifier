package com.example.lorena.challengifier.fragments.s.objective;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lorena.challengifier.R;
import com.example.lorena.challengifier.fragments.s.MainMenuFragment;
import com.example.lorena.challengifier.models.Challenge;
import com.example.lorena.challengifier.models.Objective;
import com.example.lorena.challengifier.services.business.services.Validator;
import com.example.lorena.challengifier.services.external.services.retrofit.interfaces.ObjectiveService;
import com.example.lorena.challengifier.services.external.services.services.ApiObjectiveService;
import com.example.lorena.challengifier.utils.communication.FlowAids;
import com.example.lorena.challengifier.utils.constants.ErrorMessages;
import com.example.lorena.challengifier.utils.constants.ObjStatus;
import com.example.lorena.challengifier.utils.session.SessionUser;
import com.example.lorena.challengifier.utils.tools.DateFormatter;
import com.hwangjr.rxbus.RxBus;

import java.util.Date;
import java.util.UUID;

import okhttp3.ResponseBody;
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
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Add objective");

        Challenge challenge = FlowAids.ChallengeToAddAsObjective;

        final Objective objective = new Objective();

        final EditText editTextName = (EditText) view.findViewById(R.id.editTextName);
        final EditText editTextDescription = (EditText) view.findViewById(R.id.editTextDescription);
        final EditText editTextDeadline = (EditText) view.findViewById(R.id.editTextDeadline);
        if (challenge != null) {
            editTextName.setText(challenge.getName());
            editTextDescription.setText(challenge.getDescription());
        }

        ImageView startNow = (ImageView) view.findViewById(R.id.startNow);
        startNow.setClickable(true);
        startNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Title")
                        .setMessage("Do you really want to whatever?")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.cancel();
                                objective.setStatus(ObjStatus.Ongoing.ordinal());
                                Toast.makeText(getActivity().getApplicationContext(), "Objective now in progress!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                objective.setStatus(ObjStatus.Ongoing.ordinal());
                                dialog.cancel();
                                Toast.makeText(getActivity().getApplicationContext(), "Objective saved for later!", Toast.LENGTH_SHORT).show();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        ImageView cancel = (ImageView) view.findViewById(R.id.cancel);
        cancel.setClickable(true);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RxBus.get().post(MainMenuFragment.SHOW_SCREEN, true);
            }
        });

        Button save = (Button) view.findViewById(R.id.buttonAddObjective);
        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final String title = editTextName.getText().toString();
                final String description = editTextDescription.getText().toString();
                final String deadline = editTextDeadline.getText().toString();
                int objectiveStatus = 0;
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
                    objective.setId(UUID.randomUUID());
                    objective.setName(title);
                    objective.setDeadline(deadlineDate);
                    objective.setExpectedOutcome("");
                    objective.setDescription(description);
                    objective.setUserId(SessionUser.currentUser);
                    objective.setStatus(objectiveStatus);
                    ObjectiveService service = ApiObjectiveService.getService();
                    Call<ResponseBody> call = service.addObjective(objective);
                    try {
                        call.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                Toast.makeText(getActivity().getApplicationContext(), "All set!", Toast.LENGTH_LONG).show();
                                RxBus.get().post(ObjectiveListFragment.SHOW_SCREEN, true);
                                // The network call was a success and we got a response
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Toast.makeText(getActivity().getApplicationContext(), "Oops! :(", Toast.LENGTH_LONG).show();
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
