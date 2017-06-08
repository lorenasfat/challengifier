package com.example.lorena.challengifier.fragments.s.objective;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lorena.challengifier.R;
import com.example.lorena.challengifier.models.Objective;
import com.example.lorena.challengifier.services.api.services.ApiObjectiveService;
import com.example.lorena.challengifier.services.business.services.Validator;
import com.example.lorena.challengifier.services.external.services.retrofit.interfaces.ObjectiveService;
import com.example.lorena.challengifier.utils.communication.FlowAids;
import com.example.lorena.challengifier.utils.constants.ErrorMessages;
import com.example.lorena.challengifier.utils.tools.DateFormatter;
import com.hwangjr.rxbus.RxBus;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EditObjectiveFragment extends Fragment {
    public static final String SHOW_SCREEN = "EDIT_OBJECTIVE_FRAGMENT_TAG";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_objective, container, false);

        final Objective editObjective = FlowAids.ObjectiveToEdit;

        //populate dropdown
        //final Spinner dropdown = (Spinner) view.findViewById(R.id.spinnerObjectiveStatus);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, ObjectiveHelper.getStatusesForDisplay());
        //dropdown.setAdapter(adapter);
        //dropdown.setSelection(editObjective.getStatus());

        //final int progress = 0;
        final DiscreteSeekBar slider = (DiscreteSeekBar)view.findViewById(R.id.slider);
        slider.setMax(10);
        slider.setNumericTransformer(new DiscreteSeekBar.NumericTransformer() {
            @Override
            public int transform(int value) {
                return value * 10;
            }
        });
        slider.setProgress(editObjective.getProgress());
        slider.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                //progress = value;
            }

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {

            }
        });

        final EditText editTextName = (EditText) view.findViewById(R.id.editTextName);
        final EditText editTextDescription = (EditText) view.findViewById(R.id.editTextDescription);
        final EditText editTextDeadline = (EditText) view.findViewById(R.id.editTextDeadline);
        final EditText editTextExpectedOutcome = (EditText) view.findViewById(R.id.editTextExpectedOutcome);
        if (editObjective != null) {
            editTextName.setText(editObjective.getName());
            editTextDescription.setText(editObjective.getDescription());
            editTextDeadline.setText(new SimpleDateFormat("dd-MM-yyyy").format(editObjective.getDeadline()));
        }

        Button save = (Button) view.findViewById(R.id.buttonEditObjective);
        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final String title = editTextName.getText().toString();
                final String description = editTextDescription.getText().toString();
                final String deadline = editTextDeadline.getText().toString();
                String expectedOutcome = editTextExpectedOutcome.getText().toString();
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
                    editObjective.setName(title);
                    editObjective.setExpectedOutcome(expectedOutcome);
                    editObjective.setDescription(description);
                    editObjective.setProgress(slider.getProgress());

                    ObjectiveService service = ApiObjectiveService.getService();
                    Call<Objective> call = service.editObjective(editObjective);
                    try {
                        call.enqueue(new Callback<Objective>() {
                            @Override
                            public void onResponse(Call<Objective> call, Response<Objective> response) {
                                Toast.makeText(getActivity().getApplicationContext(), "All set!", Toast.LENGTH_LONG).show();
                                RxBus.get().post(ObjectiveListFragment.SHOW_SCREEN,true);
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
