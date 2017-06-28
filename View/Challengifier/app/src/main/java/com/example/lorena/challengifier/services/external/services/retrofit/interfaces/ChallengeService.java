package com.example.lorena.challengifier.services.external.services.retrofit.interfaces;

import com.example.lorena.challengifier.models.Challenge;
import com.example.lorena.challengifier.models.MyChallenge;

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

public interface ChallengeService {
    @GET("challenge/all")
    Call<List<Challenge>> listChallenges();

    @GET("challenge/get/{id}")
    Call<List<MyChallenge>> listMyChallenges(@Path("id") String id);

    @POST("challenge/update")
    Call<Challenge> editChallenge(@Body Challenge objective);

    @POST("challenge/add")
    Call<Challenge> addChallenge(@Body Challenge objective);

    @DELETE("challenge/delete/{id}")
    Call<ResponseBody> deleteChallenge(@Path("id") UUID id);
}
