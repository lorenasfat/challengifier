package com.example.lorena.challengifier.fragments.s.challenge;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.lorena.challengifier.R;
import com.example.lorena.challengifier.models.Challenge;
import com.example.lorena.challengifier.utils.communication.FlowAids;
import com.hwangjr.rxbus.RxBus;
import com.shawnlin.numberpicker.NumberPicker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lorena on 19.06.2017.
 */

public class ViewChallengeFragment extends Fragment {

    public static final String SHOW_SCREEN = "VIEW_CHALLENGE_FRAGMENT_TAG";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_challenge, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("View challenge");

        final Challenge challenge = FlowAids.ChallengeToView;

        TextView title = (TextView)view.findViewById(R.id.textViewChallengeTitle);
        TextView description = (TextView)view.findViewById(R.id.challengeDescription);
        NumberPicker numberPicker = (NumberPicker) view.findViewById(R.id.number_picker);
        Spinner spinnerTimeUnits = (Spinner) view.findViewById(R.id.spinnerTimeUnit);
        ImageView editChellenge = (ImageView) view.findViewById(R.id.actionOnChallenge);

        editChellenge.setClickable(true);
        editChellenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FlowAids.ChallengeToEdit = FlowAids.ChallengeToView;
                RxBus.get().post(EditChallengeFragment.SHOW_SCREEN, true);

            }
        });

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
        spinnerTimeUnits.setClickable(false);
        spinnerTimeUnits.setEnabled(false);
        spinnerTimeUnits.setSelection(timeUnits.get(challenge.getSuggested_Time_UnitsId()));

        numberPicker.setEnabled(false);
        numberPicker.setClickable(false);
        numberPicker.setValue(challenge.getSuggested_Time_Number());

        title.setText(challenge.getName());
        description.setText(challenge.getDescription());

        String timeUnit = spinnerTimeUnits.getSelectedItem().toString();
        int number = numberPicker.getValue();
        return view;
    }
}
