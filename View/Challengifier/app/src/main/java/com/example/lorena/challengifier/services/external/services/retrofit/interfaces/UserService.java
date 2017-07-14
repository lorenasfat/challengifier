package com.example.lorena.challengifier.services.external.services.retrofit.interfaces;

import com.example.lorena.challengifier.models.LeaderboardUser;
import com.example.lorena.challengifier.models.SignupUser;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Lorena on 03.05.2017.
 */

public interface UserService {
    @Headers("Content-type: application/json")
    @POST("account/register")
    Call<ResponseBody> register(@Body SignupUser user);

    @GET("account/leaderboard")
    Call<List<LeaderboardUser>> listLeaderboard();
}
