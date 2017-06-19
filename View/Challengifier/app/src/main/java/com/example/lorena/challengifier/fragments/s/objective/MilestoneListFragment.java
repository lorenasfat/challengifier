package com.example.lorena.challengifier.fragments.s.objective;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.example.lorena.challengifier.R;
import com.example.lorena.challengifier.models.Milestone;
import com.example.lorena.challengifier.services.api.services.ApiMilestoneService;
import com.example.lorena.challengifier.services.external.services.retrofit.interfaces.MilestoneService;
import com.example.lorena.challengifier.utils.adapters.MilestoneListAdapter;
import com.example.lorena.challengifier.utils.communication.FlowAids;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MilestoneListFragment extends Fragment {
    List<Milestone> milestones = new ArrayList<>();
    BaseAdapter listAdapter;
    public static final String SHOW_SCREEN = "MILESTONE_LIST_FRAGMENT_TAG";

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_milestone_list, container, false);

        listAdapter = new MilestoneListAdapter(getActivity().getApplicationContext(), milestones);
        MilestoneService service = ApiMilestoneService.getService();
        Call<List<Milestone>> call = service.getMilestones(FlowAids.ObjectiveToEdit.getId());
        try {
            call.enqueue(new Callback<List<Milestone>>() {
                @Override
                public void onResponse(Call<List<Milestone>> call, Response<List<Milestone>> response) {
                    milestones.addAll(response.body());

                    /*if (milestones.size() <= 0) {
                        stateful.showEmpty("@string/no_milestone");
                    } else {*/
                        listAdapter.notifyDataSetChanged();
                        // The network call was a success and we got a response
                }

                @Override
                public void onFailure(Call<List<Milestone>> call, Throwable t) {
                    // the network call was a failure
                    // TODO: handle error
                    t.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        ListView list = (ListView) view.findViewById(R.id.milestoneList);

        list.setAdapter(listAdapter);
        registerForContextMenu(list);

        return view;
    }
}
