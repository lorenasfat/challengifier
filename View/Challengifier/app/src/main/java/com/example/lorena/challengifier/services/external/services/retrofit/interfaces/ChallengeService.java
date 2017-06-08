package com.example.lorena.challengifier.services.external.services.retrofit.interfaces;

import com.example.lorena.challengifier.models.Challenge;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Lorena on 09.01.2017.
 */

public interface ChallengeService {
    @GET("challenge/all")
    Call<List<Challenge>> listChallenges();
}
