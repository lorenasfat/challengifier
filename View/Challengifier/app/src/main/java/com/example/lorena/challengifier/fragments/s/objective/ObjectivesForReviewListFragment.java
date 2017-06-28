package com.example.lorena.challengifier.fragments.s.objective;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

import com.example.lorena.challengifier.R;
import com.example.lorena.challengifier.models.ObjectiveForReviewDto;
import com.example.lorena.challengifier.services.external.services.retrofit.interfaces.ObjectiveService;
import com.example.lorena.challengifier.services.external.services.services.ApiObjectiveService;
import com.example.lorena.challengifier.utils.adapters.ObjectivesForReviewListAdapter;
import com.example.lorena.challengifier.utils.communication.FlowAids;
import com.hwangjr.rxbus.RxBus;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ObjectivesForReviewListFragment extends Fragment {
    public static final String SHOW_SCREEN = "REVIEW_OBJECTIVE_LIST_TAG";

    ObjectivesForReviewListAdapter listAdapter;
    List<ObjectiveForReviewDto> objectives=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_objectives_for_review_list, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Objectives");

        listAdapter = new ObjectivesForReviewListAdapter(getActivity().getApplicationContext(), objectives);

        AutoCompleteTextView searchTextView = (AutoCompleteTextView)view.findViewById(R.id.autoCompleteSearch);

        ObjectiveService service = ApiObjectiveService.getService();
        Call<List<ObjectiveForReviewDto>> call = service.listObjectivesForReview(FlowAids.MyChallengeToView.getId());
        try {
            call.enqueue(new Callback<List<ObjectiveForReviewDto>>() {
                @Override
                public void onResponse(Call<List<ObjectiveForReviewDto>> call, Response<List<ObjectiveForReviewDto>> response) {
                    objectives.addAll(response.body());
                    listAdapter.notifyDataSetChanged();
                    FlowAids.ObjectivesForReviewBackup = objectives;
                    // The network call was a success and we got a response
                }

                @Override
                public void onFailure(Call<List<ObjectiveForReviewDto>> call, Throwable t) {
                    // the network call was a failure
                    // TODO: handle error
                    t.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        ListView list = (ListView) view.findViewById(R.id.objectiveList);

        list.setAdapter(listAdapter);
        registerForContextMenu(list);

        list.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> myAdapter, View myView, int position, long mylng) {
                        ObjectiveForReviewDto selectedFromList = (ObjectiveForReviewDto) (listAdapter.getItem(position));
                        FlowAids.ObjectiveToReview = selectedFromList;
                        RxBus.get().post(ReviewObjectiveFragment.SHOW_SCREEN,true);
                    }
                });

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

        return view;
    }
}
