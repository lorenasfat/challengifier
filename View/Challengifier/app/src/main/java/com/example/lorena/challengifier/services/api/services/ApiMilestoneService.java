package com.example.lorena.challengifier.services.api.services;

import com.example.lorena.challengifier.services.external.services.RetrofitService;
import com.example.lorena.challengifier.services.external.services.retrofit.interfaces.MilestoneService;

import retrofit2.Retrofit;

/**
 * Created by Lorena on 11.06.2017.
 */

public class ApiMilestoneService {
    private static Retrofit retrofit = RetrofitService.getRetrofit();
    private static MilestoneService service;

    public static MilestoneService getService(){
        if(service != null){
            return service;
        }
        service = retrofit.create(MilestoneService.class);
        return service;
    }
}
