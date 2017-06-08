package com.example.lorena.challengifier.services.api.services;

import com.example.lorena.challengifier.services.external.services.RetrofitService;
import com.example.lorena.challengifier.services.external.services.retrofit.interfaces.LoginService;

import retrofit2.Retrofit;

/**
 * Created by Lorena on 02.05.2017.
 */

public class ApiLoginService {
    private static Retrofit retrofit = RetrofitService.getBasicRetrofit();
    private static LoginService service;

    public static LoginService getService(){
        if(service != null){
            return service;
        }
        service = retrofit.create(LoginService.class);
        return service;
    }
}
