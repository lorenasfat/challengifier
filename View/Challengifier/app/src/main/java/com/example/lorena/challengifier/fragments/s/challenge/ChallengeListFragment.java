package com.example.lorena.challengifier.fragments.s.challenge;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

import com.example.lorena.challengifier.R;
import com.example.lorena.challengifier.models.Challenge;
import com.example.lorena.challengifier.services.external.services.retrofit.interfaces.ChallengeService;
import com.example.lorena.challengifier.services.external.services.services.ApiChallengeService;
import com.example.lorena.challengifier.utils.adapters.ChallengeListAdapter;
import com.example.lorena.challengifier.utils.communication.FlowAids;
import com.example.lorena.challengifier.utils.session.SessionUser;
import com.hwangjr.rxbus.RxBus;

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
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Challenges");
        setHasOptionsMenu(true);
        FlowAids.BackUpTitle ="Challenges";
        AutoCompleteTextView searchTextView = (AutoCompleteTextView) view.findViewById(R.id.autoCompleteTextView);
        searchTextView.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                listAdapter.getFilter().filter(s);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        listAdapter = new ChallengeListAdapter(getActivity().getBaseContext());
        listAdapter.setChallenges(challenges);
        ListView list = (ListView) view.findViewById(R.id.challengeList);
        list.setAdapter(listAdapter);

        loadChallenges();
        list.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> myAdapter, View myView, int position, long mylng) {
                        Challenge selectedFromList = (Challenge) (listAdapter.getItem(position));
                        FlowAids.ChallengeToView = selectedFromList;
                        RxBus.get().post(ViewChallengeFragment.SHOW_SCREEN, true);
                    }
                }
        );

        return view;
    }

    private void loadChallenges() {
        challenges.clear();

        ChallengeService service = ApiChallengeService.getService();
        Call<List<Challenge>> call = service.listChallenges();
        try {
            call.enqueue(new Callback<List<Challenge>>() {
                @Override
                public void onResponse(Call<List<Challenge>> call, Response<List<Challenge>> response) {
                    challenges.addAll(response.body());
                    listAdapter.setChallenges(challenges);
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
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.challenge_menu, menu);
    }

    public void showMyChallenges() {
        List<Challenge> objectives = FlowAids.ChallengesBackup;
        List<Challenge> sorted = new ArrayList<>();

        for (Challenge challenge : objectives) {
            if (challenge.getUser_Id().equalsIgnoreCase(SessionUser.getLoggedInUser().getAspNetUserId()))
                sorted.add(challenge);
        }
        listAdapter.setChallenges(sorted);
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.action_refresh:
                loadChallenges();
                return true;
            case R.id.action_mine:
                showMyChallenges();
                return true;
            default:
                return false;
        }
    }
}
