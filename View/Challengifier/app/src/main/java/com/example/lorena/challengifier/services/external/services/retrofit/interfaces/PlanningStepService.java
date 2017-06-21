package com.example.lorena.challengifier.services.external.services.retrofit.interfaces;

import com.example.lorena.challengifier.models.Objective;
import com.example.lorena.challengifier.models.PlanningStep;

import java.util.List;
import java.util.UUID;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Lorena on 21.06.2017.
 */

public interface PlanningStepService {
    @GET("objective/all")
    Call<List<PlanningStep>> listObjectives();

    @POST("objective/update")
    Call<PlanningStep> editObjective(@Body Objective objective);

    @POST("objective/add")
    Call<PlanningStep> addObjective(@Body Objective objective);

    @DELETE("objective/delete/{id}")
    Call<ResponseBody> deleteObjective(@Path("id") UUID id);
}
