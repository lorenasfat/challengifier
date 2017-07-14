package com.example.lorena.challengifier.fragments.s.user;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

import com.example.lorena.challengifier.R;
import com.example.lorena.challengifier.models.LeaderboardUser;
import com.example.lorena.challengifier.services.external.services.RetrofitService;
import com.example.lorena.challengifier.services.external.services.retrofit.interfaces.UserService;
import com.example.lorena.challengifier.utils.adapters.LeaderboardAdapter;
import com.example.lorena.challengifier.utils.communication.FlowAids;
import com.example.lorena.challengifier.utils.session.SessionUser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaderboardFragment extends Fragment {
    public static final String SHOW_SCREEN = "LEADERBOARD_TAG";

    LeaderboardAdapter listAdapter;
    ListView listView;
    List<LeaderboardUser> leaderboardUsers = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_leaderboard, container, false);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle("Leaderboard");

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

        listAdapter = new LeaderboardAdapter(getActivity().getBaseContext());
        listAdapter.setLeaderboardUsers(leaderboardUsers);
        listView = (ListView) view.findViewById(R.id.leaderboardList);
        listView.setAdapter(listAdapter);

        loadUsers();

        return view;
    }

    private int findMe() {
        for (int i = 0; i< leaderboardUsers.size(); i++) {
            if(leaderboardUsers.get(i).getUsername().equalsIgnoreCase(SessionUser.loggedInUser.getUsername()))
                return i;
        }
        return 0;
    }

    private void loadUsers() {
        leaderboardUsers.clear();

        UserService service = RetrofitService.getRetrofit().create(UserService.class);
        Call<List<LeaderboardUser>> call = service.listLeaderboard();
        try {
            call.enqueue(new Callback<List<LeaderboardUser>>() {
                @Override
                public void onResponse(Call<List<LeaderboardUser>> call, Response<List<LeaderboardUser>> response) {
                    leaderboardUsers.addAll(response.body());
                    listAdapter.setLeaderboardUsers(leaderboardUsers);
                    listView.setSelection(findMe());
                    listAdapter.notifyDataSetChanged();
                    FlowAids.LeaderboardUsersBackup = leaderboardUsers;
                    // The network call was a success and we got a response
                }

                @Override
                public void onFailure(Call<List<LeaderboardUser>> call, Throwable t) {
                    // the network call was a failure
                    // TODO: handle error
                    t.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
