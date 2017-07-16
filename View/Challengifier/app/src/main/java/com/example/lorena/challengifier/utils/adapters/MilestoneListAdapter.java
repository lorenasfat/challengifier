package com.example.lorena.challengifier.utils.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lorena.challengifier.R;
import com.example.lorena.challengifier.models.Milestone;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lorena on 11.06.2017.
 */

public class MilestoneListAdapter extends BaseAdapter {

    List<Milestone> milestones = new ArrayList<>();
    Context context;

    public List<Milestone> getMilestones() {
        return milestones;
    }

    public void setMilestones(List<Milestone> milestones) {
        this.milestones = milestones;
    }

    public MilestoneListAdapter(Context context, List<Milestone> milestones){
        this.context = context;
        this.milestones = milestones;
    }
    @Override
    public int getCount() {
        return milestones.size();
    }

    @Override
    public Object getItem(int position) {
        return milestones.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // get a reference to the LayoutInflater service
        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // check if we can reuse a previously defined cell which now is not visible anymore
        View myRow = (convertView == null) ?
                inflater.inflate(R.layout.milestone_item, parent, false) : convertView;
        // get the visual elements and update them with the information from the model

        TextView title = (TextView) myRow.findViewById(R.id.milestoneTitle);
        title.setText(milestones.get(position).getName());
        TextView description = (TextView) myRow.findViewById(R.id.descriptionText);
        description.setText(milestones.get(position).getDescription());
        TextView sinceDate = (TextView) myRow.findViewById(R.id.sinceDate);
        sinceDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(milestones.get(position).getStartDate()));
        TextView untilDate = (TextView) myRow.findViewById(R.id.untilDate);
        untilDate.setText(new SimpleDateFormat("yyyy-MM-dd").format((milestones.get(position).getEndDate())));

        return myRow;
    }
}
