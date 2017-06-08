package com.example.lorena.challengifier.services.api.services;

import com.example.lorena.challengifier.services.external.services.RetrofitService;
import com.example.lorena.challengifier.services.external.services.retrofit.interfaces.ObjectiveService;

import retrofit2.Retrofit;

/**
 * Created by Lorena on 04.06.2017.
 */

public class ApiObjectiveService {
    private static Retrofit retrofit = RetrofitService.getRetrofit();
    private static ObjectiveService service;

    public static ObjectiveService getService(){
        if(service != null){
            return service;
        }
        service = retrofit.create(ObjectiveService.class);
        return service;
    }
}
