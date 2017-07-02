package com.example.lorena.challengifier.fragments.s.milestone;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lorena.challengifier.R;
import com.example.lorena.challengifier.fragments.s.objective.ViewObjectiveFragment;
import com.example.lorena.challengifier.fragments.s.planning.step.PlanningStepListFragment;
import com.example.lorena.challengifier.models.Milestone;
import com.example.lorena.challengifier.services.business.services.Validator;
import com.example.lorena.challengifier.services.external.services.retrofit.interfaces.MilestoneService;
import com.example.lorena.challengifier.services.external.services.services.ApiMilestoneService;
import com.example.lorena.challengifier.utils.communication.FlowAids;
import com.example.lorena.challengifier.utils.constants.ErrorMessages;
import com.example.lorena.challengifier.utils.tools.DateFormatter;
import com.hwangjr.rxbus.RxBus;

import java.util.Date;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.lorena.challengifier.utils.communication.FlowAids.TempToBeAdded;


public class AddMilestoneFragment extends Fragment {
    public static final String SHOW_SCREEN = "ADD_MILESTONE_FRAGMENT_TAG";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_milestone, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Add milestone");

        final EditText title = (EditText) view.findViewById(R.id.milestoneTitle);
        final EditText description = (EditText) view.findViewById(R.id.milesoneDescription);
        final EditText since = (EditText) view.findViewById(R.id.sinceInput);
        final EditText until = (EditText) view.findViewById(R.id.untilInput);
        ImageView link = (ImageView) view.findViewById(R.id.linkToPLanningStep);

        if(FlowAids.ObjectiveToEdit.getChallengeId() == null)
            link.setVisibility(View.INVISIBLE);

        if (FlowAids.TempToBeAdded != null) {
            title.setText(TempToBeAdded.getName());
            description.setText(TempToBeAdded.getDescription());
        }

        ImageView cancel = (ImageView) view.findViewById(R.id.cancel);
        cancel.setClickable(true);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RxBus.get().post(ViewObjectiveFragment.SHOW_SCREEN, true);
            }
        });

        link.setClickable(true);
        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FlowAids.LinkChallengeToObjective = true;
                String titleStr = title.getText().toString();
                String descriptionStr = description.getText().toString();

                Milestone milestone = new Milestone();
                milestone.setName(titleStr);
                milestone.setDescription(descriptionStr);
                milestone.setObjectiveId(FlowAids.ObjectiveToEdit.getId());

                TempToBeAdded = milestone;
                RxBus.get().post(PlanningStepListFragment.SHOW_SCREEN, true);
            }
        });

        Button save = (Button) view.findViewById(R.id.buttonAddMilestone);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String titleStr = title.getText().toString();
                final String descriptionStr = description.getText().toString();
                final String sinceStr = since.getText().toString();
                final String untilStr = until.getText().toString();

                Date sinceDate = null;
                Date untilDate = null;

                boolean ok = true;
                if (Validator.isEmpty(titleStr)) {
                    title.setError(ErrorMessages.EMPTY);
                    ok = false;
                }
                if (Validator.isEmpty(descriptionStr)) {
                    description.setError(ErrorMessages.EMPTY);
                    ok = false;
                }
                if (Validator.isEmpty(sinceStr)) {
                    since.setError(ErrorMessages.EMPTY);
                    ok = false;
                }
                if (Validator.isEmpty(untilStr)) {
                    until.setError(ErrorMessages.EMPTY);
                    ok = false;
                }
                if (!Validator.isDate(untilStr)) {
                    until.setError(ErrorMessages.WRONG_DATE_FORMAT);
                    ok = false;
                } else {
                    untilDate = DateFormatter.getDate(untilStr);
                }
                if (!Validator.isDate(sinceStr)) {
                    since.setError(ErrorMessages.WRONG_DATE_FORMAT);
                    ok = false;
                } else {
                    sinceDate = DateFormatter.getDate(sinceStr);
                }

                if (ok) {
                    Milestone milestone = new Milestone();
                    milestone.setName(titleStr);
                    milestone.setDescription(descriptionStr);
                    milestone.setEndDate(untilDate);
                    milestone.setStartDate(sinceDate);
                    milestone.setObjectiveId(FlowAids.ObjectiveToEdit.getId());
                    if (FlowAids.TempToBeAdded != null)
                        milestone.setPlanningStepId(FlowAids.TempToBeAdded.getPlanningStepId());

                    //todo check for planning step

                    MilestoneService service = ApiMilestoneService.getService();
                    Call<ResponseBody> call = service.addMilestone(milestone);
                    try {
                        call.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                Toast.makeText(getActivity().getApplicationContext(), "All set!", Toast.LENGTH_LONG).show();
                                RxBus.get().post(MilestoneListFragment.SHOW_SCREEN, true);
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
