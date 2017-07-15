package com.example.lorena.challengifier.fragments.s.challenge;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
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
import com.example.lorena.challengifier.services.external.services.services.ApiChallengeService;
import com.example.lorena.challengifier.services.external.services.retrofit.interfaces.ChallengeService;
import com.example.lorena.challengifier.utils.communication.FlowAids;
import com.hwangjr.rxbus.RxBus;
import com.shawnlin.numberpicker.NumberPicker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditChallengeFragment extends Fragment {
    public static final String SHOW_SCREEN = "EDIT_CHALLENGE_FRAGMENT_TAG";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_challenge, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Edit challenge");
        FlowAids.BackUpTitle ="Edit challenge";
        final Challenge editChallenge = FlowAids.ChallengeToEdit;

        final EditText title = (EditText)view.findViewById(R.id.textViewChallengeTitle);
        title.setText(editChallenge.getName());
        final EditText description = (EditText)view.findViewById(R.id.challengeDescription);
        description.setText(editChallenge.getDescription());
        final NumberPicker numberPicker = (NumberPicker) view.findViewById(R.id.number_picker);
        numberPicker.setValue(editChallenge.getSuggested_Time_Number());
        final Spinner spinnerTimeUnits = (Spinner) view.findViewById(R.id.spinnerTimeUnit);

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
        spinnerTimeUnits.setSelection(timeUnits.get(editChallenge.getSuggested_Time_UnitsId()));

        Button save = (Button) view.findViewById(R.id.buttonEditChallenge);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editChallenge.setDescription(description.getText().toString());
                editChallenge.setName(title.getText().toString());
                editChallenge.setSuggested_Time_Number(numberPicker.getValue());
                editChallenge.setSuggested_Time_UnitsId(spinnerTimeUnits.getSelectedItem().toString());

                ChallengeService service = ApiChallengeService.getService();
                Call<Challenge> call = service.editChallenge(editChallenge);
                try {
                    call.enqueue(new Callback<Challenge>() {
                        @Override
                        public void onResponse(Call<Challenge> call, Response<Challenge> response) {
                            Toast.makeText(getActivity().getApplicationContext(), "All set!", Toast.LENGTH_LONG).show();
                            RxBus.get().post(ChallengeListFragment.SHOW_SCREEN, true);
                            // The network call was a success and we got a response
                        }

                        @Override
                        public void onFailure(Call<Challenge> call, Throwable t) {
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
