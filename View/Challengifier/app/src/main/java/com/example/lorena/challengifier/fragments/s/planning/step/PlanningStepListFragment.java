package com.example.lorena.challengifier.fragments.s.planning.step;


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
import com.example.lorena.challengifier.fragments.s.challenge.ViewChallengeFragment;
import com.example.lorena.challengifier.fragments.s.milestone.AddMilestoneFragment;
import com.example.lorena.challengifier.models.PlanningStep;
import com.example.lorena.challengifier.services.external.services.retrofit.interfaces.PlanningStepService;
import com.example.lorena.challengifier.services.external.services.services.ApiPlanningStepService;
import com.example.lorena.challengifier.utils.adapters.PlanningStepListAdapter;
import com.example.lorena.challengifier.utils.communication.FlowAids;
import com.example.lorena.challengifier.utils.session.SessionUser;
import com.hwangjr.rxbus.RxBus;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlanningStepListFragment extends Fragment {
    public static final String SHOW_SCREEN = "PLANNINGSTEP_LIST_FRAGMENT_TAG";
    List<PlanningStep> planningSteps = new ArrayList<>();
    BaseAdapter listAdapter;

    public PlanningStepListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_planning_step_list, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Planning Steps");

        listAdapter = new PlanningStepListAdapter(getActivity().getApplicationContext(), planningSteps);
        PlanningStepService service = ApiPlanningStepService.getService();
        UUID challengeId = null;
        if (FlowAids.IsLinkChallengeToObjective) {
            challengeId = FlowAids.ObjectiveToEdit.getChallengeId();
        } else {
            challengeId = FlowAids.ChallengeToView.getId();
        }

        Call<List<PlanningStep>> call = service.listPlanningSteps(challengeId);
        ImageView addPlanningStep = (ImageView) view.findViewById(R.id.addPlanningStepButton);
        addPlanningStep.setClickable(true);
        addPlanningStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RxBus.get().post(AddPlanningStepFragment.SHOW_SCREEN, true);
            }
        });

        if (!FlowAids.IsMyObjectives) {
            addPlanningStep.setVisibility(View.INVISIBLE);
        }

        try {
            call.enqueue(new Callback<List<PlanningStep>>() {
                @Override
                public void onResponse(Call<List<PlanningStep>> call, Response<List<PlanningStep>> response) {
                    planningSteps.addAll(response.body());

                    if (planningSteps.isEmpty() && FlowAids.IsLinkChallengeToObjective) {
                        Toast.makeText(getActivity().getApplicationContext(), "No milestones, no restrictions!", Toast.LENGTH_LONG).show();
                        RxBus.get().post(AddMilestoneFragment.SHOW_SCREEN, true);
                    }

                    if (planningSteps.isEmpty()) {
                        Toast.makeText(getActivity().getApplicationContext(), "Looks like there are no planning steps!", Toast.LENGTH_LONG).show();
                        RxBus.get().post(ViewChallengeFragment.SHOW_SCREEN, true);
                    } else {
                        listAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<List<PlanningStep>> call, Throwable t) {
                    // the network call was a failure
                    // TODO: handle error
                    t.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        ListView list = (ListView) view.findViewById(R.id.planningStepList);

        list.setAdapter(listAdapter);

        if (FlowAids.ChallengeToView.getUser_Id().equalsIgnoreCase(SessionUser.loggedInUser.getAspNetUserId()))
            registerForContextMenu(list);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                if (FlowAids.IsLinkChallengeToObjective) {
                    FlowAids.TempToBeAdded.setPlanningStepId(((PlanningStep) listAdapter.getItem(position)).getId());
                    Toast.makeText(getActivity().getApplicationContext(), "Planning step linked!", Toast.LENGTH_LONG).show();
                    RxBus.get().post(AddMilestoneFragment.SHOW_SCREEN, true);
                }
            }
        });
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
            PlanningStep step = (PlanningStep) listAdapter.getItem(info.position);

            PlanningStepService service = ApiPlanningStepService.getService();
            Call<ResponseBody> call = service.deletePlanningStep(step.getId());
            try {
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Toast.makeText(getActivity().getApplicationContext(), "Planning step deleted!", Toast.LENGTH_LONG).show();
                        RxBus.get().post(PlanningStepListFragment.SHOW_SCREEN, true);
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
