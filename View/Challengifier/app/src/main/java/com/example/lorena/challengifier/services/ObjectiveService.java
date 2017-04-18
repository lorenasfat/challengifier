package com.example.lorena.challengifier.services;

import com.example.lorena.challengifier.models.Objective;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Lorena on 09.01.2017.
 */

public interface ObjectiveService {
    @GET("objective/all")
    Call<List<Objective>> listObjectives();
}
