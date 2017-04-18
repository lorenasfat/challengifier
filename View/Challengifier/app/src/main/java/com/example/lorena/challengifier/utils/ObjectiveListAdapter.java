package com.example.lorena.challengifier.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lorena.challengifier.R;
import com.example.lorena.challengifier.models.Objective;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lorena on 11.01.2017.
 */

public class ObjectiveListAdapter extends BaseAdapter {

    List<Objective> objectives=new ArrayList<>();
    private Context context;

    public ObjectiveListAdapter(Context context,List<Objective> objectives) {
        this.context = context;
        this.objectives = objectives;
    }

    public List<Objective> getObjectives(){ return objectives;}

    public void setObjectives(List<Objective> objectives) {
        this.objectives = objectives;
    }

    @Override
    public int getCount() {
        return objectives.size();
    }

    @Override
    public Object getItem(int position) {
        return objectives.get(position);
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
                inflater.inflate(R.layout.objective_item, parent, false) : convertView;
        // get the visual elements and update them with the information from the model
        TextView title = (TextView)myRow.findViewById(R.id.textViewObjectiveTitle);
        title.setText(objectives.get(position).getName());

        return myRow;
    }
}
