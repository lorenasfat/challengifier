package com.example.lorena.challengifier.activities.challenge;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.lorena.challengifier.models.Challenge;
import com.example.lorena.challengifier.utils.adapters.ChallengeListAdapter;

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
        /*setContentView(R.layout.activity_challenge_list);
        activity = this;

        challenges = new ArrayList<Challenge>();

        listAdapter = new ChallengeListAdapter(this.getBaseContext());
        listAdapter.setChallenges(challenges);

        ChallengeService service = ApiChallengeService.getService();
        Call<List<Challenge>> call = service.listChallenges();
        try {
            call.enqueue(new Callback<List<Challenge>>() {
                @Override
                public void onResponse(Call<List<Challenge>> call, Response<List<Challenge>> response) {
                    challenges.addAll(response.body());
                    listAdapter.notifyDataSetChanged();
                    // The network call was a success and we got a response
                }

                @Override
                public void onFailure(Call<List<Challenge>> call, Throwable t) {
                    // the network call was a failure
                    // TODO: handle error
                    t.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


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
        }*///);
    }
}
