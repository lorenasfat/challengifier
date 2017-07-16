package com.example.lorena.challengifier.utils.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.lorena.challengifier.R;
import com.example.lorena.challengifier.models.Objective;
import com.example.lorena.challengifier.utils.communication.FlowAids;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lorena on 11.01.2017.
 */

public class ObjectiveListAdapter extends BaseAdapter implements Filterable {

    List<Objective> objectives = new ArrayList<>();
    private Context context;

    public ObjectiveListAdapter(Context context, List<Objective> objectives) {
        this.context = context;
        this.objectives = objectives;
    }

    public List<Objective> getObjectives() {
        return objectives;
    }

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
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                if (constraint == null || constraint.length() == 0) {
// No filter implemented we return all the list
                    objectives = FlowAids.ObjectivesBackup;
                    results.values = objectives;
                    results.count = objectives.size();
                }
                else{
                    ArrayList<Objective> searched = new ArrayList<>();
                    for (Objective o : objectives) {
                        if (o.getName().toUpperCase().contains(constraint.toString().toUpperCase()))
                            searched.add(o);
                    }

                    results.values = searched;
                    results.count = searched.size();
                }
                return results;
            }
            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results.count == 0)
                    notifyDataSetInvalidated();
                else {
                    objectives = (List<Objective>)results.values;
                    notifyDataSetChanged();
                }
            }
        };
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // get a reference to the LayoutInflater service
        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // check if we can reuse a previously defined cell which now is not visible anymore
        View myRow =
                inflater.inflate(R.layout.objective_item, parent, false) ;
        // get the visual elements and update them with the information from the model
        TextView title = (TextView) myRow.findViewById(R.id.textViewObjectiveTitle);
        title.setText(objectives.get(position).getName());

        return myRow;
    }


}

