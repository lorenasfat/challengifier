package com.example.lorena.challengifier.fragments.s.milestone;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lorena.challengifier.R;
import com.example.lorena.challengifier.models.Milestone;
import com.example.lorena.challengifier.models.Objective;
import com.example.lorena.challengifier.services.external.services.retrofit.interfaces.MilestoneService;
import com.example.lorena.challengifier.services.external.services.services.ApiMilestoneService;
import com.example.lorena.challengifier.utils.adapters.MilestoneListAdapter;
import com.example.lorena.challengifier.utils.communication.FlowAids;
import com.hwangjr.rxbus.RxBus;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import okhttp3.ResponseBody;
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
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Milestones");
        FlowAids.BackUpTitle = "Milestones";
        listAdapter = new MilestoneListAdapter(getActivity().getApplicationContext(), milestones);
        MilestoneService service = ApiMilestoneService.getService();

        UUID src = null;
        if (FlowAids.IsRatingOn)
            src = FlowAids.ObjectiveToReview.getId();
        else
            src = FlowAids.ObjectiveToView.getId();

        Call<List<Milestone>> call = service.getMilestones(src);

        ImageView addMilestones = (ImageView) view.findViewById(R.id.addMilestones);
        addMilestones.setClickable(true);
        addMilestones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RxBus.get().post(AddMilestoneFragment.SHOW_SCREEN, true);
            }
        });
        if (FlowAids.IsRatingOn)
            addMilestones.setVisibility(View.INVISIBLE);
        milestones.clear();
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
        if (!FlowAids.IsRatingOn)
            registerForContextMenu(list);

        return view;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, v.getId(), 0, "DELETE");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle() == "DELETE") {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Milestone mile = (Milestone) listAdapter.getItem(info.position);

            MilestoneService service = ApiMilestoneService.getService();
            Call<ResponseBody> call = service.deleteMilestone(mile.getId());
            try {
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Toast.makeText(getActivity().getApplicationContext(), "Milestone deleted!", Toast.LENGTH_LONG).show();
                        RxBus.get().post(MilestoneListFragment.SHOW_SCREEN, true);
                        // The network call was a success and we got a response
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(getActivity().getApplicationContext(), "Oops! :(", Toast.LENGTH_LONG).show();
                        // the network call was a failure
                        // TODO: handle error
                        t.printStackTrace();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
