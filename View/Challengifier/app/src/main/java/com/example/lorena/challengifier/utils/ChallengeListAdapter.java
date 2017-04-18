package com.example.lorena.challengifier.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lorena.challengifier.R;
import com.example.lorena.challengifier.models.Challenge;

import java.util.List;

/**
 * Created by Lorena on 02.12.2016.
 */

public class ChallengeListAdapter extends BaseAdapter {
    List<Challenge> challenges;
    private Context context;

    public ChallengeListAdapter(Context context) {
        this.context = context;
    }

    public List<Challenge> getChallenges() {
        return challenges;
    }

    public void setChallenges(List<Challenge> challenges) {
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
        title.setText(challenges.get(position).getTitle());

        return myRow;
    }
}
