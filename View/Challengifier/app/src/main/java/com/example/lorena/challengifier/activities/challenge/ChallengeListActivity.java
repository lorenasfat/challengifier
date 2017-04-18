package com.example.lorena.challengifier.activities.challenge;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.lorena.challengifier.activities.objective.AddObjectiveActivity;
import com.example.lorena.challengifier.utils.ChallengeListAdapter;
import com.example.lorena.challengifier.R;
import com.example.lorena.challengifier.models.Challenge;
import com.example.lorena.challengifier.utils.temp.ChallengeGenerator;

import java.text.ParseException;
import java.util.List;

public class ChallengeListActivity extends AppCompatActivity {
    ChallengeListAdapter listAdapter;
    List<Challenge> challenges;// = ChallengeGenerator.getChallenges();
    Activity activity;

    public ChallengeListActivity() throws ParseException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge_list);
        activity = this;

        try {
            ChallengeGenerator.setChallenges();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        challenges = ChallengeGenerator.getChallenges();

        listAdapter = new ChallengeListAdapter(this.getBaseContext());
        listAdapter.setChallenges(challenges);

        ListView list = (ListView) findViewById(R.id.challengeList);

        list.setAdapter(listAdapter);
        registerForContextMenu(list);

        //ImageView img = (ImageView) findViewById(R.id.imageViewMore);
        list.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> myAdapter, View myView, int position, long mylng) {
                        Challenge selectedFromList = (Challenge) (listAdapter.getItem(position));
                        Intent intent = new Intent(activity, ViewChallengeActivity.class);
                        intent.putExtra("ViewChallenge", selectedFromList);
                        startActivity(intent);
                    }
                }

                /*new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ViewChallengeActivity.class);
                intent.putExtra("ViewChallenge", (Challenge)list.getSelectedItem());
                startActivityForResult(intent,0);
            }
        }*/);
    }


}
