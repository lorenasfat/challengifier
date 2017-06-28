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
import com.example.lorena.challengifier.models.ObjectiveForReviewDto;
import com.example.lorena.challengifier.utils.communication.FlowAids;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lorena on 28.06.2017.
 */

public class ObjectivesForReviewListAdapter extends BaseAdapter implements Filterable {
    List<ObjectiveForReviewDto> objectives = new ArrayList<>();
    private Context context;

    public ObjectivesForReviewListAdapter(Context context, List<ObjectiveForReviewDto> objectives) {
        this.context = context;
        this.objectives = objectives;
    }

    public List<ObjectiveForReviewDto> getObjectives() {
        return objectives;
    }

    public void setObjectives(List<ObjectiveForReviewDto> objectives) {
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
                    objectives = FlowAids.ObjectivesForReviewBackup;
                    results.values = objectives;
                    results.count = objectives.size();
                }
                else{
                    ArrayList<ObjectiveForReviewDto> searched = new ArrayList<>();
                    for (ObjectiveForReviewDto o : objectives) {
                        if (o.getObjectiveName().toUpperCase().startsWith(constraint.toString().toUpperCase()))
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
                    objectives = (List<ObjectiveForReviewDto>)results.values;
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
        View myRow = (convertView == null) ?
                inflater.inflate(R.layout.objective_review_item, parent, false) : convertView;
        // get the visual elements and update them with the information from the model
        TextView title = (TextView) myRow.findViewById(R.id.textViewObjectiveTitle);
        title.setText(objectives.get(position).getObjectiveName());
        TextView by = (TextView) myRow.findViewById(R.id.textViewBy);
        String userName=objectives.get(position).getUsername();
        by.setText(userName.substring(0,userName.indexOf("@")));
        return myRow;
    }
}
