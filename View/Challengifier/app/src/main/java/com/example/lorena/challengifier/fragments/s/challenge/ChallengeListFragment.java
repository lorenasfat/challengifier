package com.example.lorena.challengifier.fragments.s.challenge;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.lorena.challengifier.R;
import com.example.lorena.challengifier.models.Challenge;
import com.example.lorena.challengifier.services.api.services.ApiChallengeService;
import com.example.lorena.challengifier.services.external.services.retrofit.interfaces.ChallengeService;
import com.example.lorena.challengifier.utils.adapters.ChallengeListAdapter;
import com.example.lorena.challengifier.utils.communication.FlowAids;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChallengeListFragment extends Fragment {
    public static final String SHOW_SCREEN = "CHALLENGE_LIST_FRAGMENT_TAG";

    ChallengeListAdapter listAdapter;
    List<Challenge> challenges = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_challenge_list, container, false);

        listAdapter = new ChallengeListAdapter(getActivity().getBaseContext());
        listAdapter.setChallenges(challenges);

        ChallengeService service = ApiChallengeService.getService();
        Call<List<Challenge>> call = service.listChallenges();
        try {
            call.enqueue(new Callback<List<Challenge>>() {
                @Override
                public void onResponse(Call<List<Challenge>> call, Response<List<Challenge>> response) {
                    challenges.addAll(response.body());
                    listAdapter.notifyDataSetChanged();
                    FlowAids.ChallengesBackup = challenges;
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

        ListView list = (ListView) view.findViewById(R.id.challengeList);

        list.setAdapter(listAdapter);
        registerForContextMenu(list);

        list.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> myAdapter, View myView, int position, long mylng) {
                        Challenge selectedFromList = (Challenge) (listAdapter.getItem(position));
                        //Intent intent = new Intent(activity, ViewChallengeActivity.class);
                        //intent.putExtra("ViewChallenge", selectedFromList);
                        //startActivity(intent);
                    }
                }
            );

        return view;
    }
}
