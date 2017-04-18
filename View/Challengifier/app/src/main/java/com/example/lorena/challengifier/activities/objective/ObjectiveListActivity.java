package com.example.lorena.challengifier.activities.objective;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.lorena.challengifier.R;
import com.example.lorena.challengifier.activities.challenge.ViewChallengeActivity;
import com.example.lorena.challengifier.models.Challenge;
import com.example.lorena.challengifier.models.Objective;
import com.example.lorena.challengifier.services.ObjectiveService;
import com.example.lorena.challengifier.services.external.RetrofitService;
import com.example.lorena.challengifier.utils.ChallengeListAdapter;
import com.example.lorena.challengifier.utils.ObjectiveListAdapter;
import com.example.lorena.challengifier.utils.temp.ObjectiveGenerator;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ObjectiveListActivity extends AppCompatActivity {

    //filter my objectives: all, completed
    Activity activity;
    ObjectiveListAdapter listAdapter;
    List<Objective> objectives=new ArrayList<>();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objective_list);
        activity = this;
        listAdapter = new ObjectiveListAdapter(this.getBaseContext(),objectives);

        /*try {
            ObjectiveGenerator.setObjectives();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        objectives = ObjectiveGenerator.getObjectives();
*/
        Retrofit retrofit = RetrofitService.getRetrofit();
        ObjectiveService service = retrofit.create(ObjectiveService.class);
        Call<List<Objective>> call = service.listObjectives();
        try {
            call.enqueue(new Callback<List<Objective>>() {
                @Override
                public void onResponse(Call<List<Objective>> call, Response<List<Objective>> response) {
                    objectives.addAll(response.body());
                    listAdapter.notifyDataSetChanged();
                    // The network call was a success and we got a response
                    // TODO: use the repository list and display it
                }

                @Override
                public void onFailure(Call<List<Objective>> call, Throwable t) {
                    // the network call was a failure
                    // TODO: handle error
                    t.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*List<Objective> objectives = null;
        try {
            objectives = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }*/



        ListView list = (ListView) findViewById(R.id.objectiveList);

        list.setAdapter(listAdapter);
        registerForContextMenu(list);

        list.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> myAdapter, View myView, int position, long mylng) {
                        Objective selectedFromList = (Objective) (listAdapter.getItem(position));
                        Intent intent = new Intent(activity, ViewObjectiveActivity.class);
                        intent.putExtra("ViewObjective", selectedFromList);
                        startActivity(intent);
                    }
                });
    }
}
