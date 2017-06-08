package com.example.lorena.challengifier.services.external.services.retrofit.interfaces;

import com.example.lorena.challengifier.models.Objective;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Lorena on 09.01.2017.
 */

public interface ObjectiveService {
    @GET("objective/all")
    Call<List<Objective>> listObjectives();

    @POST("objective/update")
    Call<Objective> editObjective(@Body Objective objective);

    @POST("objective/add")
    Call<Objective> addObjective(@Body Objective objective);
}
