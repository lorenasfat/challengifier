package com.example.lorena.challengifier.activities.objective;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.lorena.challengifier.R;
import com.example.lorena.challengifier.models.Objective;
import com.example.lorena.challengifier.services.external.services.retrofit.interfaces.ObjectiveService;
import com.example.lorena.challengifier.services.external.services.RetrofitService;
import com.example.lorena.challengifier.utils.ObjectiveListAdapter;

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
        listAdapter = new ObjectiveListAdapter(this.getBaseContext(), objectives);

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
