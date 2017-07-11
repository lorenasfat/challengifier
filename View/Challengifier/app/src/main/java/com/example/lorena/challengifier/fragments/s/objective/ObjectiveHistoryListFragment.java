package com.example.lorena.challengifier.fragments.s.objective;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

import com.example.lorena.challengifier.R;
import com.example.lorena.challengifier.models.ObjectiveHistory;
import com.example.lorena.challengifier.services.external.services.retrofit.interfaces.ObjectiveService;
import com.example.lorena.challengifier.services.external.services.services.ApiObjectiveService;
import com.example.lorena.challengifier.utils.adapters.HistoryObjectiveListAdapter;
import com.example.lorena.challengifier.utils.communication.FlowAids;
import com.example.lorena.challengifier.utils.session.SessionUser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ObjectiveHistoryListFragment extends Fragment {
        public static final String SHOW_SCREEN = "HISTORY_OBJECTIVE_LIST_TAG";

        HistoryObjectiveListAdapter listAdapter;
        List<ObjectiveHistory> objectives = new ArrayList<>();

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.fragment_objective_history_list, container, false);
            ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
            actionBar.setTitle("History of objectives");

            listAdapter = new HistoryObjectiveListAdapter(getActivity().getApplicationContext(), objectives);

            AutoCompleteTextView searchTextView = (AutoCompleteTextView) view.findViewById(R.id.autoCompleteSearch);

            loadObjectives();

            ListView list = (ListView) view.findViewById(R.id.objectiveList);

            list.setAdapter(listAdapter);
            registerForContextMenu(list);
            return view;
        }

    private void loadObjectives() {
        objectives.clear();
        ObjectiveService service = ApiObjectiveService.getService();
        Call<List<ObjectiveHistory>> call = service.listHistoryObjectives(SessionUser.loggedInUser.getAspNetUserId());
        try {
            call.enqueue(new Callback<List<ObjectiveHistory>>() {
                @Override
                public void onResponse(Call<List<ObjectiveHistory>> call, Response<List<ObjectiveHistory>> response) {
                    objectives.addAll(response.body());
                    listAdapter.setObjectives(objectives);
                    listAdapter.notifyDataSetChanged();
                    FlowAids.HistoryObjectivesBackup = objectives;
                    // The network call was a success and we got a response
                }

                @Override
                public void onFailure(Call<List<ObjectiveHistory>> call, Throwable t) {
                    // the network call was a failure
                    // TODO: handle error
                    t.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        listAdapter.notifyDataSetChanged();
    }
}
