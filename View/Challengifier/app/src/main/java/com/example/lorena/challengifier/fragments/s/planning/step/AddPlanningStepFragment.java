package com.example.lorena.challengifier.fragments.s.planning.step;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lorena.challengifier.R;
import com.example.lorena.challengifier.models.PlanningStep;
import com.example.lorena.challengifier.services.external.services.retrofit.interfaces.PlanningStepService;
import com.example.lorena.challengifier.services.external.services.services.ApiPlanningStepService;
import com.example.lorena.challengifier.utils.communication.FlowAids;
import com.hwangjr.rxbus.RxBus;
import com.shawnlin.numberpicker.NumberPicker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddPlanningStepFragment extends Fragment {
    public static final String SHOW_SCREEN = "ADD_PLANNING_STEP_FRAGMENT_TAG";

    public AddPlanningStepFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_planning_step, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Add planning step");

        final TextView title = (TextView)view.findViewById(R.id.textViewPSTitle);
        final TextView description = (TextView)view.findViewById(R.id.psDescription);
        final NumberPicker numberPicker = (NumberPicker) view.findViewById(R.id.number_picker);
        final Spinner spinnerTimeUnits = (Spinner) view.findViewById(R.id.spinnerTimeUnit);

        final PlanningStep planningStep = new PlanningStep();

        List<String> list = new ArrayList<String>();
        list.add("day");
        list.add("week");
        list.add("month");
        list.add("year");

        Map<String, Integer> timeUnits = new HashMap<>();
        timeUnits.put("day",0);
        timeUnits.put("week",1);
        timeUnits.put("month",2);
        timeUnits.put("year",3);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTimeUnits.setAdapter(adapter);

        Button save = (Button) view.findViewById(R.id.buttonAddPS);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                planningStep.setDescription(description.getText().toString());
                planningStep.setName(title.getText().toString());
                planningStep.setTimeUnitNumber(numberPicker.getValue());
                planningStep.setTimeUnitId(spinnerTimeUnits.getSelectedItem().toString());
                planningStep.setId(UUID.randomUUID());
                planningStep.setChallengeId(FlowAids.ChallengeToView.getId());

                PlanningStepService service = ApiPlanningStepService.getService();
                Call<ResponseBody> call = service.addPlanningStep(planningStep);
                try {
                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            Toast.makeText(getActivity().getApplicationContext(), "All set!", Toast.LENGTH_LONG).show();
                            RxBus.get().post(PlanningStepListFragment.SHOW_SCREEN, true);
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
        });

        return view;
    }

}
