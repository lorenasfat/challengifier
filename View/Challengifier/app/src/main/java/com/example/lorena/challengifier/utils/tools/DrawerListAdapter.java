package com.example.lorena.challengifier.utils.tools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lorena.challengifier.R;

import java.util.List;

/**
 * Created by Lorena on 09.05.2017.
 */

public class DrawerListAdapter extends BaseAdapter {
    List<String> options;
    private Context context;

    public DrawerListAdapter(Context context, List<String> options) {
        this.context = context;
        this.options = options;
    }

    public List<String> getChallenges() {
        return options;
    }

    public void setChallenges(List<String> challenges) {
        this.options = challenges;
    }

    @Override
    public int getCount() {
        return options.size();
    }

    @Override
    public Object getItem(int position) {
        return options.get(position);
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
        title.setText(options.get(position));

        return myRow;
    }

}
