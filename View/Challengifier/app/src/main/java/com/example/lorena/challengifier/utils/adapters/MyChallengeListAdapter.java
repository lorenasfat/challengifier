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
import com.example.lorena.challengifier.models.MyChallenge;
import com.example.lorena.challengifier.utils.communication.FlowAids;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lorena on 27.06.2017.
 */

public class MyChallengeListAdapter extends BaseAdapter implements Filterable {
    List<MyChallenge> challenges;
    private Context context;

    public MyChallengeListAdapter(Context context) {
        this.context = context;
    }

    public List<MyChallenge> getChallenges() {
        return challenges;
    }

    public void setChallenges(List<MyChallenge> challenges) {
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
        View myRow = inflater.inflate(R.layout.challenge_item, parent, false);
        // get the visual elements and update them with the information from the model
        TextView title = (TextView)myRow.findViewById(R.id.textViewTitle);
        title.setText(challenges.get(position).getName());

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
                    challenges = FlowAids.MyChallengesBackup;
                    results.values = challenges;
                    results.count = challenges.size();
                }
                else{
                    ArrayList<MyChallenge> searched = new ArrayList<>();
                    for (MyChallenge o : challenges) {
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
                    challenges = (List<MyChallenge>)results.values;
                    notifyDataSetChanged();
                }
            }
        };
    }
}
