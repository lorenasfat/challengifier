package com.example.lorena.challengifier.fragments.s.objective;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lorena.challengifier.R;
import com.example.lorena.challengifier.models.UserRating;
import com.example.lorena.challengifier.services.external.services.retrofit.interfaces.ObjectiveService;
import com.example.lorena.challengifier.services.external.services.services.ApiObjectiveService;
import com.example.lorena.challengifier.utils.communication.FlowAids;
import com.example.lorena.challengifier.utils.session.SessionUser;
import com.hwangjr.rxbus.RxBus;

import java.util.UUID;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ReviewObjectiveFragment extends Fragment {
    public static final String SHOW_SCREEN = "REVIEW_OBJECTIVE_FRAGMENT_TAG";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_review_objective, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(FlowAids.ObjectiveToReview.getObjectiveName());

        String userName = FlowAids.ObjectiveToReview.getUsername();
        TextView username = (TextView) view.findViewById(R.id.textViewUsername);
        username.setText(userName.substring(0,userName.indexOf("@")));

        TextView description = (TextView) view.findViewById(R.id.textViewDesription);
        description.setText(FlowAids.ObjectiveToReview.getDescription());

        TextView from = (TextView) view.findViewById(R.id.textViewFrom);
        from.setText(FlowAids.ObjectiveToReview.getFrom().toString());

        TextView to = (TextView) view.findViewById(R.id.textViewTo);
        to.setText(FlowAids.ObjectiveToReview.getTo().toString());

        ImageView viewMilestones = (ImageView) view.findViewById(R.id.vireMilestones);
        viewMilestones.setClickable(true);
        viewMilestones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo
            }
        });

        final MaterialRatingBar rating = (MaterialRatingBar) view.findViewById(R.id.materialRatingBar);

        Button saveRating = (Button) view.findViewById(R.id.buttonRate);
        saveRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int rate = rating.getProgress();

                UserRating rating = new UserRating();
                rating.setId(UUID.randomUUID());
                rating.setGrade(rate);
                rating.setUserId(SessionUser.loggedInUser.getAspNetUserId());
                rating.setObjectiveId(FlowAids.ObjectiveToReview.getId());

                ObjectiveService service = ApiObjectiveService.getService();
                Call<ResponseBody> call = service.rateObjective(rating);
                try {
                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            Toast.makeText(getActivity().getApplicationContext(), "All set!", Toast.LENGTH_LONG).show();
                            RxBus.get().post(ObjectivesForReviewListFragment.SHOW_SCREEN, true);
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
        });
        return view;
    }
}
