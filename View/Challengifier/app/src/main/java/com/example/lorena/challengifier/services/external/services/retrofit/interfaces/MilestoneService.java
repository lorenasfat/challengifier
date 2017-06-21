package com.example.lorena.challengifier.services.external.services.retrofit.interfaces;

import com.example.lorena.challengifier.models.Milestone;

import java.util.List;
import java.util.UUID;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Lorena on 11.06.2017.
 */

public interface MilestoneService {
    @Headers("Content-type: application/json")
    @GET("milestone/all/{objectiveId}")
    Call<List<Milestone>> getMilestones(@Path("objectiveId") UUID objectiveId);

    @POST("milestone/add")
    Call<ResponseBody> addMilestone(@Body Milestone milestone);

    @DELETE("milestone/delete/{id}")
    Call<ResponseBody> deleteMilestone(@Path("id") UUID id);
}
