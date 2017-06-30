package com.example.lorena.challengifier.fragments.s.objective;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lorena.challengifier.R;
import com.example.lorena.challengifier.fragments.s.milestone.MilestoneListFragment;
import com.example.lorena.challengifier.utils.communication.FlowAids;
import com.hwangjr.rxbus.RxBus;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;


public class ViewObjectiveFragment extends Fragment {
    public static final String SHOW_SCREEN = "VIEW_OBJECTIVE_FRAGMENT_TAG";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_objective, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Edit objective");

        //final int progress = 0;
        final DiscreteSeekBar slider = (DiscreteSeekBar) view.findViewById(R.id.slider);
        slider.setMax(10);
        slider.setNumericTransformer(new DiscreteSeekBar.NumericTransformer() {
            @Override
            public int transform(int value) {
                return value * 10;
            }
        });
        slider.setProgress(FlowAids.ObjectiveToView.getProgress());

        ImageView viewMilestones = (ImageView) view.findViewById(R.id.imageViewMilestones);
        final TextView editTextName = (TextView) view.findViewById(R.id.editTextName);
        final TextView editTextDescription = (TextView) view.findViewById(R.id.editTextDescription);
        final TextView editTextDeadline = (TextView) view.findViewById(R.id.editTextDeadline);

        ImageView markCompleted = (ImageView) view.findViewById(R.id.markCompleted);
        markCompleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
                RxBus.get().post(ObjectiveListFragment.SHOW_SCREEN, true);
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
}
