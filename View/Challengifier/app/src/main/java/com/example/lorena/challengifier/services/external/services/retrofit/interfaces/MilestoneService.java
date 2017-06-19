package com.example.lorena.challengifier.services.external.services.retrofit.interfaces;

import com.example.lorena.challengifier.models.Milestone;

import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by Lorena on 11.06.2017.
 */

public interface MilestoneService {
    @Headers("Content-type: application/json")
    @GET("milestone/all/{objectiveId}")
    Call<List<Milestone>> getMilestones(@Path("objectiveId") UUID objectiveId);
}
