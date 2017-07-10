package com.example.lorena.challengifier.fragments.s.challenge;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lorena.challengifier.R;
import com.example.lorena.challengifier.fragments.s.objective.AddObjectiveFragment;
import com.example.lorena.challengifier.fragments.s.planning.step.PlanningStepListFragment;
import com.example.lorena.challengifier.models.Challenge;
import com.example.lorena.challengifier.utils.communication.FlowAids;
import com.example.lorena.challengifier.utils.session.SessionUser;
import com.hwangjr.rxbus.RxBus;

import static com.example.lorena.challengifier.utils.communication.FlowAids.IsChallengeAccepted;

/**
 * Created by Lorena on 19.06.2017.
 */

public class ViewChallengeFragment extends Fragment {

    public static final String SHOW_SCREEN = "VIEW_CHALLENGE_FRAGMENT_TAG";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_challenge, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("View challenge");

        if(FlowAids.ChallengeToView.getUser_Id().equalsIgnoreCase(SessionUser.loggedInUser.getAspNetUserId()))
            setHasOptionsMenu(true);

        final Challenge challenge = FlowAids.ChallengeToView;

        TextView title = (TextView)view.findViewById(R.id.textViewChallengeTitle);
        TextView description = (TextView)view.findViewById(R.id.challengeDescription);
        ImageView viewPlanningStept = (ImageView) view.findViewById(R.id.viewPlanningStepsButton);

        viewPlanningStept.setClickable(true);
        viewPlanningStept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FlowAids.IsMyObjectives = false;
                FlowAids.IsLinkChallengeToObjective = false;
                RxBus.get().post(PlanningStepListFragment.SHOW_SCREEN, true);
            }
        });

        TextView suggestedTime = (TextView) view.findViewById(R.id.textViewTimeInterval);
        suggestedTime.setText(challenge.getSuggested_Time_Number() +" "+challenge.getSuggested_Time_UnitsId());

        title.setText(challenge.getName());
        description.setText(challenge.getDescription());

        ImageView acceptChallenge = (ImageView) view.findViewById(R.id.actionAcceptChallenge);
        acceptChallenge.setClickable(true);

        acceptChallenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IsChallengeAccepted = true;
                RxBus.get().post(AddObjectiveFragment.SHOW_SCREEN,true);
            }
        });

        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.edit_challenge_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.action_edit:
                FlowAids.ChallengeToEdit = FlowAids.ChallengeToView;
                RxBus.get().post(EditChallengeFragment.SHOW_SCREEN, true);
                return true;
            default:
                return false;
        }
    }
}
