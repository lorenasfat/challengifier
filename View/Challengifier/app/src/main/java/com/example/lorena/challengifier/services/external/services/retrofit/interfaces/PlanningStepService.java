package com.example.lorena.challengifier.services.external.services.retrofit.interfaces;

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
    @GET("planningstep/get/{id}")
    Call<List<PlanningStep>> listPlanningSteps(@Path("id") UUID id);

    @POST("planningstep/update")
    Call<PlanningStep> editPlanningStep(@Body PlanningStep planningStep);

    @POST("planningstep/add")
    Call<ResponseBody> addPlanningStep(@Body PlanningStep planningStep);

    @DELETE("planningstep/delete/{id}")
    Call<ResponseBody> deletePlanningStep(@Path("id") UUID id);
}
