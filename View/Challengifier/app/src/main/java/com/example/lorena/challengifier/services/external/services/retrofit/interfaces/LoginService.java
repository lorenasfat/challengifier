package com.example.lorena.challengifier.services.external.services.retrofit.interfaces;

import com.example.lorena.challengifier.models.LoginUser;
import com.example.lorena.challengifier.models.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Lorena on 02.05.2017.
 */

public interface LoginService {
    @Headers("Content-type: application/json")
    @POST("account/login")
    Call<ResponseBody> login(@Body LoginUser user);

    @POST("account/info")
    Call<User> getInfo(@Body String username);
}
