package com.example.lorena.challengifier.fragments.s.challenge;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lorena.challengifier.R;
import com.example.lorena.challengifier.fragments.s.objective.ObjectiveListFragment;
import com.example.lorena.challengifier.models.Challenge;
import com.example.lorena.challengifier.services.external.services.retrofit.interfaces.ChallengeService;
import com.example.lorena.challengifier.services.external.services.services.ApiChallengeService;
import com.example.lorena.challengifier.utils.adapters.ChallengeListAdapter;
import com.example.lorena.challengifier.utils.communication.FlowAids;
import com.hwangjr.rxbus.RxBus;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
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

        listAdapter = new ChallengeListAdapter(getActivity().getBaseContext());
        listAdapter.setChallenges(challenges);

        challenges.clear();

        ChallengeService service = ApiChallengeService.getService();
        Call<List<Challenge>> call = service.listChallenges();
        try {
            call.enqueue(new Callback<List<Challenge>>() {
                @Override
                public void onResponse(Call<List<Challenge>> call, Response<List<Challenge>> response) {
                    challenges.addAll(response.body());
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

        ListView list = (ListView) view.findViewById(R.id.challengeList);

        list.setAdapter(listAdapter);
        registerForContextMenu(list);

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


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, v.getId(), 0, "DELETE");
    }
    @Override
    public boolean onContextItemSelected(MenuItem item){
        if(item.getTitle()=="DELETE"){
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Challenge chal = (Challenge)listAdapter.getItem(info.position);

            ChallengeService service = ApiChallengeService.getService();
            Call<ResponseBody> call = service.deleteChallenge(chal.getId());
            try {
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Toast.makeText(getActivity().getApplicationContext(), "Challenge deleted!", Toast.LENGTH_LONG).show();
                        RxBus.get().post(ObjectiveListFragment.SHOW_SCREEN, true);
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
