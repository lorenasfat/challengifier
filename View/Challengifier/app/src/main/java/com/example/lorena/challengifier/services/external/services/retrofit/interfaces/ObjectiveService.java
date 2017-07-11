package com.example.lorena.challengifier.services.external.services.retrofit.interfaces;

import com.example.lorena.challengifier.models.Objective;
import com.example.lorena.challengifier.models.ObjectiveForReviewDto;
import com.example.lorena.challengifier.models.ObjectiveHistory;
import com.example.lorena.challengifier.models.User;
import com.example.lorena.challengifier.models.UserRating;

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
 * Created by Lorena on 09.01.2017.
 */

public interface ObjectiveService {
    @GET("objective/all/{id}")
    Call<List<Objective>> listObjectives(@Path("id") String id);

    @GET("objective/history/{id}")
    Call<List<ObjectiveHistory>> listHistoryObjectives(@Path("id") String id);

    @GET("objective/review/{id}")
    Call<List<ObjectiveForReviewDto>> listObjectivesForReview(@Path("id") UUID id);

    @POST("objective/update")
    Call<User> editObjective(@Body Objective objective);

    @POST("objective/rate")
    Call<ResponseBody> rateObjective(@Body UserRating rating);

    @POST("objective/add")
    Call<ResponseBody> addObjective(@Body Objective objective);

    @DELETE("objective/delete/{id}")
    Call<ResponseBody> deleteObjective(@Path("id") UUID id);
}
