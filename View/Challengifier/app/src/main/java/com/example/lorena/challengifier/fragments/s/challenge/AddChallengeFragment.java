package com.example.lorena.challengifier.fragments.s.challenge;


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
import com.example.lorena.challengifier.models.Challenge;
import com.example.lorena.challengifier.services.business.services.Validator;
import com.example.lorena.challengifier.services.external.services.retrofit.interfaces.ChallengeService;
import com.example.lorena.challengifier.services.external.services.services.ApiChallengeService;
import com.example.lorena.challengifier.utils.communication.FlowAids;
import com.example.lorena.challengifier.utils.constants.ErrorMessages;
import com.example.lorena.challengifier.utils.session.SessionUser;
import com.hwangjr.rxbus.RxBus;
import com.shawnlin.numberpicker.NumberPicker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddChallengeFragment extends Fragment {
    public static final String SHOW_SCREEN = "ADD_CHALLENGE_FRAGMENT_TAG";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_challenge, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Add challenge");
        FlowAids.BackUpTitle = "Add challenge";

        final TextView title = (TextView)view.findViewById(R.id.textViewChallengeTitle);
        final TextView description = (TextView)view.findViewById(R.id.challengeDescription);
        final NumberPicker numberPicker = (NumberPicker) view.findViewById(R.id.number_picker);
        final Spinner spinnerTimeUnits = (Spinner) view.findViewById(R.id.spinnerTimeUnit);

        final Challenge challenge = new Challenge();

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


        Button save = (Button) view.findViewById(R.id.buttonAddChallenge);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String desc = description.getText().toString();
                String tit = title.getText().toString();
                boolean ok = true;
                if(Validator.isEmpty(desc)){
                    title.setError(ErrorMessages.EMPTY);
                    ok = false;
                }
                if(Validator.isEmpty(tit)){
                    description.setError(ErrorMessages.EMPTY);
                    ok = false;
                }

                if(ok) {
                    challenge.setDescription(desc);
                    challenge.setName(tit);
                    challenge.setSuggested_Time_Number(numberPicker.getValue());
                    challenge.setSuggested_Time_UnitsId(spinnerTimeUnits.getSelectedItem().toString());
                    challenge.setId(UUID.randomUUID());
                    challenge.setUser_Id(SessionUser.loggedInUser.getAspNetUserId());

                    ChallengeService service = ApiChallengeService.getService();
                    Call<Challenge> call = service.addChallenge(challenge);
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
            }
        });

        return view;
    }

}
