package com.example.lorena.challengifier.services.external.services.services;

import com.example.lorena.challengifier.services.external.services.retrofit.interfaces.ChallengeService;
import com.example.lorena.challengifier.services.external.services.RetrofitService;

import retrofit2.Retrofit;

/**
 * Created by Lorena on 09.01.2017.
 */

public class ApiChallengeService {
    private static Retrofit retrofit = RetrofitService.getRetrofit();
    private static ChallengeService service;

    public static ChallengeService getService(){
        if(service != null){
            return service;
        }
        service = retrofit.create(ChallengeService.class);
        return service;
    }
}
