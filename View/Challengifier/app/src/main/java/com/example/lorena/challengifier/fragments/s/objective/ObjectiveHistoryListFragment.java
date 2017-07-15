package com.example.lorena.challengifier.fragments.s.objective;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lorena.challengifier.R;
import com.example.lorena.challengifier.models.ObjectiveHistory;
import com.example.lorena.challengifier.services.external.services.retrofit.interfaces.ObjectiveService;
import com.example.lorena.challengifier.services.external.services.services.ApiObjectiveService;
import com.example.lorena.challengifier.utils.adapters.HistoryObjectiveListAdapter;
import com.example.lorena.challengifier.utils.communication.FlowAids;
import com.example.lorena.challengifier.utils.constants.ObjStatus;
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
        setHasOptionsMenu(true);
        FlowAids.BackUpTitle="History of objectives";
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

                    if (objectives.isEmpty()) {
                        ObjectiveHistory empty = new ObjectiveHistory();
                        empty.setName("No past objectives :(");
                        objectives.add(empty);
                    }
                    listAdapter.setObjectives(objectives);
                    listAdapter.notifyDataSetChanged();
                    FlowAids.HistoryObjectivesBackup = objectives;
                    // The network call was a success and we got a response
                }

                @Override
                public void onFailure(Call<List<ObjectiveHistory>> call, Throwable t) {
                    // the network call was a failure
                    Toast.makeText(getActivity().getApplicationContext(), "Oops! :(", Toast.LENGTH_LONG).show();
                    t.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.objective_history_menu, menu);
    }

    private void showReviewed() {
        List<ObjectiveHistory> objectives = FlowAids.HistoryObjectivesBackup;
        List<ObjectiveHistory> filtered = new ArrayList<>();
        for (ObjectiveHistory obj : objectives) {
            if (obj.getStatus() == ObjStatus.Reviewed.ordinal())
                filtered.add(obj);
        }
        listAdapter.setObjectives(filtered);
        listAdapter.notifyDataSetChanged();
    }

    private void showPastDeadline() {
        List<ObjectiveHistory> objectives = FlowAids.HistoryObjectivesBackup;
        List<ObjectiveHistory> filtered = new ArrayList<>();
        for (ObjectiveHistory obj : objectives) {
            if (obj.getStatus() == ObjStatus.Past_Deadline.ordinal())
                filtered.add(obj);
        }
        listAdapter.setObjectives(filtered);
        listAdapter.notifyDataSetChanged();
    }

    private void showCompleted() {
        List<ObjectiveHistory> objectives = FlowAids.HistoryObjectivesBackup;
        List<ObjectiveHistory> filtered = new ArrayList<>();
        for (ObjectiveHistory obj : objectives) {
            if (obj.getStatus() == ObjStatus.Completed.ordinal())
                filtered.add(obj);
        }
        listAdapter.setObjectives(filtered);
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.action_refresh:
                loadObjectives();
                return true;
            case R.id.action_reviewed:
                showReviewed();
                return true;
            case R.id.action_due:
                showPastDeadline();
                return true;
            case R.id.action_completed:
                showCompleted();
                return true;
            default:
                return false;
        }
    }
}





