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
import com.example.lorena.challengifier.models.ArchivedChallenge;
import com.example.lorena.challengifier.utils.communication.FlowAids;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lorena on 02.12.2016.
 */

public class ArchivedChallengeListAdapter extends BaseAdapter implements Filterable {
    List<ArchivedChallenge> challenges;
    private Context context;

    public ArchivedChallengeListAdapter(Context context) {
        this.context = context;
    }

    public List<ArchivedChallenge> getChallenges() {
        return challenges;
    }

    public void setChallenges(List<ArchivedChallenge> challenges) {
        this.challenges = challenges;
    }

    @Override
    public int getCount() {
        return challenges.size();
    }

    @Override
    public Object getItem(int position) {
        return challenges.get(position);
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
        View myRow = inflater.inflate(R.layout.archived_challenge_item, parent, false);
        // get the visual elements and update them with the information from the model

        TextView title = (TextView)myRow.findViewById(R.id.textViewTitle);
        title.setText(challenges.get(position).getName());

        TextView description = (TextView)myRow.findViewById(R.id.textViewDescription);
        description.setText(challenges.get(position).getDescription());

        TextView acceptedBy = (TextView)myRow.findViewById(R.id.textViewAcceptedBy);
        acceptedBy.setText("Accepted by "+challenges.get(position).getAcceptedBy()+" users.");

        return myRow;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                if (constraint == null || constraint.length() == 0) {
// No filter implemented we return all the list
                    challenges = FlowAids.MyArchivedChallengesBackup;
                    results.values = challenges;
                    results.count = challenges.size();
                }
                else{
                    ArrayList<ArchivedChallenge> searched = new ArrayList<>();
                    for (ArchivedChallenge o : challenges) {
                        if (o.getName().toUpperCase().startsWith(constraint.toString().toUpperCase()))
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
                    challenges = (List<ArchivedChallenge>)results.values;
                    notifyDataSetChanged();
                }
            }
        };
    }
}
