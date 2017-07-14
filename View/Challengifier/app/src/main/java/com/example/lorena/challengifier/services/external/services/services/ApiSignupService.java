package com.example.lorena.challengifier.services.external.services.services;

import com.example.lorena.challengifier.services.external.services.RetrofitService;
import com.example.lorena.challengifier.services.external.services.retrofit.interfaces.UserService;

import retrofit2.Retrofit;

/**
 * Created by Lorena on 03.05.2017.
 */

public class ApiSignupService {
    private static Retrofit retrofit = RetrofitService.getBasicRetrofit();
    private static UserService service;

    public static UserService getService(){
        if(service != null){
            return service;
        }
        service = retrofit.create(UserService.class);
        return service;
    }
}
