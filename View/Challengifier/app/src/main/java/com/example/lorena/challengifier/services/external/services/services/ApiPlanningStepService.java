package com.example.lorena.challengifier.services.external.services.services;

import com.example.lorena.challengifier.services.external.services.RetrofitService;
import com.example.lorena.challengifier.services.external.services.retrofit.interfaces.PlanningStepService;

import retrofit2.Retrofit;

/**
 * Created by Lorena on 21.06.2017.
 */

public class ApiPlanningStepService {
    private static Retrofit retrofit = RetrofitService.getRetrofit();
    private static PlanningStepService service;

    public static PlanningStepService getService(){
        if(service != null){
            return service;
        }
        service = retrofit.create(PlanningStepService.class);
        return service;
    }
}
