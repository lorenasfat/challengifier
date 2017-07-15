package com.example.lorena.challengifier.fragments.s.challenge;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lorena.challengifier.R;
import com.example.lorena.challengifier.models.ArchivedChallenge;
import com.example.lorena.challengifier.services.external.services.retrofit.interfaces.ChallengeService;
import com.example.lorena.challengifier.services.external.services.services.ApiChallengeService;
import com.example.lorena.challengifier.utils.adapters.ArchivedChallengeListAdapter;
import com.example.lorena.challengifier.utils.communication.FlowAids;
import com.example.lorena.challengifier.utils.session.SessionUser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Lorena on 10.07.2017.
 */

public class ArchivedChallengeListFragment extends Fragment {
    public static final String SHOW_SCREEN = "ARCHIVED_CHALLENGE_LIST_FRAGMENT_TAG";

    ArchivedChallengeListAdapter listAdapter;
    List<ArchivedChallenge> challenges = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_archived_challenges_list, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Archived Challenges");
        setHasOptionsMenu(true);
        FlowAids.BackUpTitle = "Archived Challenges";

        listAdapter = new ArchivedChallengeListAdapter(getActivity().getBaseContext());
        listAdapter.setChallenges(challenges);
        ListView list = (ListView) view.findViewById(R.id.challengeList);
        list.setAdapter(listAdapter);
        loadArchivedChallenges();

        return view;
    }

    private void loadArchivedChallenges() {
        challenges.clear();

        ChallengeService service = ApiChallengeService.getService();
        Call<List<ArchivedChallenge>> call = service.listMyArchivedChallenges(SessionUser.loggedInUser.getAspNetUserId());
        try {
            call.enqueue(new Callback<List<ArchivedChallenge>>() {
                @Override
                public void onResponse(Call<List<ArchivedChallenge>> call, Response<List<ArchivedChallenge>> response) {
                    challenges.addAll(response.body());

                    if(challenges.isEmpty()){
                        ArchivedChallenge empty = new ArchivedChallenge();
                        empty.setName("No archived challenges :(");
                        challenges.add(empty);
                    }

                    listAdapter.setChallenges(challenges);
                    listAdapter.notifyDataSetChanged();
                    FlowAids.MyArchivedChallengesBackup = challenges;
                    // The network call was a success and we got a response
                }

                @Override
                public void onFailure(Call<List<ArchivedChallenge>> call, Throwable t) {
                    // the network call was a failure
                    Toast.makeText(getActivity().getApplicationContext(), "Oops! :(", Toast.LENGTH_LONG).show();
                    t.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
