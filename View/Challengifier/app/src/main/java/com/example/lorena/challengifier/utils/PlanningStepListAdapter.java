package com.example.lorena.challengifier.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lorena.challengifier.R;
import com.example.lorena.challengifier.models.PlanningStep;

import java.util.List;

/**
 * Created by Lorena on 14.01.2017.
 */

public class PlanningStepListAdapter extends BaseAdapter {
    List<PlanningStep> planningSteps;
    private Context context;

    public PlanningStepListAdapter(Context context) {
        this.context = context;
    }

    public List<PlanningStep> getPlanningSteps() {
        return planningSteps;
    }

    public void setPlanningSteps(List<PlanningStep> planningSteps) {
        this.planningSteps = planningSteps;
    }

    @Override
    public int getCount() {
        return planningSteps.size();
    }

    @Override
    public Object getItem(int position) {
        return planningSteps.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // check if we can reuse a previously defined cell which now is not visible anymore
        View myRow = inflater.inflate(R.layout.planningstep_item, parent, false);
        // get the visual elements and update them with the information from the model
        TextView title = (TextView)myRow.findViewById(R.id.nameTextView);
        title.setText(planningSteps.get(position).getName());

        TextView description = (TextView)myRow.findViewById(R.id.descriptionTextView);
        description.setText(planningSteps.get(position).getDescription());


        TextView start = (TextView)myRow.findViewById(R.id.durationNrTextView);
        start.setText(planningSteps.get(position).getTimeUnitNumber());

        TextView end = (TextView)myRow.findViewById(R.id.durationTextView);
        end.setText(planningSteps.get(position).getTimeUnitId());
        return myRow;
    }
}
