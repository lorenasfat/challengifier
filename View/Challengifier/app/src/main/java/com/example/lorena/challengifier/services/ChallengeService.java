package com.example.lorena.challengifier.services;

import com.example.lorena.challengifier.models.Challenge;
import com.example.lorena.challengifier.models.Objective;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Lorena on 09.01.2017.
 */

public interface ChallengeService {
    @GET("challenge/all")
    Call<List<Challenge>> listChallenges();
}
