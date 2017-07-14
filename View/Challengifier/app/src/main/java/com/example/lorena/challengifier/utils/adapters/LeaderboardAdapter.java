package com.example.lorena.challengifier.utils.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.lorena.challengifier.R;
import com.example.lorena.challengifier.models.LeaderboardUser;
import com.example.lorena.challengifier.utils.communication.FlowAids;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lorena on 13.07.2017.
 */

public class LeaderboardAdapter extends BaseAdapter implements Filterable{

    List<LeaderboardUser> leaderboardUsers = new ArrayList<>();
    Context context;

    public List<LeaderboardUser> getLeaderboardUsers() {
        return leaderboardUsers;
    }

    public void setLeaderboardUsers(List<LeaderboardUser> leaderboardUsers) {
        this.leaderboardUsers = leaderboardUsers;
    }

    public LeaderboardAdapter(Context context) {
        this.context = context;
    }
    public LeaderboardAdapter(Context context, List<LeaderboardUser> leaderboardUsers) {
        this.context = context;
        this.leaderboardUsers = leaderboardUsers;
    }

    @Override
    public int getCount() {
        return leaderboardUsers.size();
    }

    @Override
    public Object getItem(int position) {
        return leaderboardUsers.get(position);
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
                inflater.inflate(R.layout.leaderboard_item, parent, false) : convertView;
        // get the visual elements and update them with the information from the model

        String userName = leaderboardUsers.get(position).getUsername();
        String formatted = userName.substring(0, userName.indexOf("@"));

        TextView name = (TextView) myRow.findViewById(R.id.textViewName);
        name.setText((position+1) +". "+ formatted);

        TextView points = (TextView) myRow.findViewById(R.id.textViewPoints);
        points.setText(leaderboardUsers.get(position).getPoints()+" points");

        if (position % 2 == 0) {
            myRow.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
            name.setTextColor(Color.WHITE);
            points.setTextColor(Color.WHITE);
        }

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
                    leaderboardUsers = FlowAids.LeaderboardUsersBackup;
                    results.values = leaderboardUsers;
                    results.count = leaderboardUsers.size();
                }
                else{
                    ArrayList<LeaderboardUser> searched = new ArrayList<>();
                    for (LeaderboardUser o : leaderboardUsers) {
                        if (o.getUsername().toUpperCase().contains(constraint.toString().toUpperCase()))
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
                    leaderboardUsers = (List<LeaderboardUser>)results.values;
                    notifyDataSetChanged();
                }
            }
        };
    }
}
