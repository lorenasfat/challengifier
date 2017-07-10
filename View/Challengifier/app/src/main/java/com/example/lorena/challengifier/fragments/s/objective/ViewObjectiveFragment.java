package com.example.lorena.challengifier.fragments.s.objective;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.example.lorena.challengifier.R;
import com.example.lorena.challengifier.fragments.s.milestone.MilestoneListFragment;
import com.example.lorena.challengifier.models.Objective;
import com.example.lorena.challengifier.services.external.services.retrofit.interfaces.ObjectiveService;
import com.example.lorena.challengifier.services.external.services.services.ApiObjectiveService;
import com.example.lorena.challengifier.utils.communication.FlowAids;
import com.example.lorena.challengifier.utils.constants.ObjStatus;
import com.example.lorena.challengifier.utils.constants.ObjectiveHelper;
import com.hwangjr.rxbus.RxBus;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ViewObjectiveFragment extends Fragment {
    public static final String SHOW_SCREEN = "VIEW_OBJECTIVE_FRAGMENT_TAG";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_objective, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("View objective");

        //final int progress = 0;
        /*final DiscreteSeekBar slider = (DiscreteSeekBar) view.findViewById(R.id.slider);
        slider.setMax(10);
        slider.setNumericTransformer(new DiscreteSeekBar.NumericTransformer() {
            @Override
            public int transform(int value) {
                return value * 10;
            }
        });
        slider.setProgress(FlowAids.ObjectiveToView.getProgress());*/

        final RoundCornerProgressBar progressBar = (RoundCornerProgressBar) view.findViewById(R.id.progressBar);
        progressBar.setMax(100);
        progressBar.setProgress(FlowAids.ObjectiveToView.getProgress() * 10);
        progressBar.setSecondaryProgress(FlowAids.ObjectiveToView.getProgress() * 10 + 10);

        ImageView viewMilestones = (ImageView) view.findViewById(R.id.imageViewMilestones);
        final TextView editTextName = (TextView) view.findViewById(R.id.editTextName);
        final TextView editTextDescription = (TextView) view.findViewById(R.id.editTextDescription);
        final TextView editTextDeadline = (TextView) view.findViewById(R.id.editTextDeadline);
        boolean isStarted = true;

        ImageView markCompleted = (ImageView) view.findViewById(R.id.markCompleted);

        if (FlowAids.ObjectiveToView.getStartDate() == null || FlowAids.ObjectiveToView.getStatus() == ObjStatus.Not_Active.ordinal()) {
            markCompleted.setImageResource(R.drawable.start);
            isStarted = false;
        }

        final TextView status = (TextView) view.findViewById(R.id.editTextStatus);
        status.setText(ObjectiveHelper.getStatusName(FlowAids.ObjectiveToView.getStatus()));

        final boolean finalIsStarted = isStarted;
        markCompleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (finalIsStarted) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Objective completed?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    FlowAids.ObjectiveToView.setStatus(ObjStatus.Completed.ordinal());
                                    FlowAids.ObjectiveToView.setEndDate(new Date());
                                    FlowAids.ObjectiveToView.setProgress(10);
                                    progressBar.setProgress(FlowAids.ObjectiveToView.getProgress() * 10);
                                    status.setText("Completed");
                                    updateObjective(finalIsStarted);
                                    dialog.cancel();
                                }
                            })
                            .setNegativeButton("Later", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Do you want to start the objective now?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    FlowAids.ObjectiveToView.setStartDate(new Date());
                                    FlowAids.ObjectiveToView.setStatus(ObjStatus.Ongoing.ordinal());
                                    status.setText("Ongoing");
                                    updateObjective(finalIsStarted);
                                    dialog.cancel();
                                }
                            })
                            .setNegativeButton("Later", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }

            }
        });

        ImageView edit = (ImageView) view.findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FlowAids.ObjectiveToEdit = FlowAids.ObjectiveToView;
                RxBus.get().post(EditObjectiveFragment.SHOW_SCREEN, true);
            }
        });

        viewMilestones.setClickable(true);
        viewMilestones.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RxBus.get().post(MilestoneListFragment.SHOW_SCREEN, true);
                    }
                }
        );

        editTextName.setText(FlowAids.ObjectiveToView.getName());
        editTextDeadline.setText(FlowAids.ObjectiveToView.getDeadline().toString());
        editTextDescription.setText(FlowAids.ObjectiveToView.getDescription());

        return view;
    }

    private void updateObjective(final boolean finalIsStarted) {
        ObjectiveService service = ApiObjectiveService.getService();
        Call<Objective> call = service.editObjective(FlowAids.ObjectiveToView);
        try {
            call.enqueue(new Callback<Objective>() {
                @Override
                public void onResponse(Call<Objective> call, Response<Objective> response) {
                    if (finalIsStarted) {
                        Toast.makeText(getActivity().getApplicationContext(), "Congratulations!", Toast.LENGTH_LONG).show();
                        RxBus.get().post(ObjectiveListFragment.SHOW_SCREEN, true);
                        // The network call was a success and we got a response
                    } else {
                        Toast.makeText(getActivity().getApplicationContext(), "Good luck!", Toast.LENGTH_LONG).show();
                        RxBus.get().post(ViewObjectiveFragment.SHOW_SCREEN, true);
                        // The network call was a success and we got a response
                    }
                }

                @Override
                public void onFailure(Call<Objective> call, Throwable t) {
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
