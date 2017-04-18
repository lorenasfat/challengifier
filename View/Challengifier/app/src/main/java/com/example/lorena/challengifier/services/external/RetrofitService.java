package com.example.lorena.challengifier.services.external;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Lorena on 09.01.2017.
 */

public class RetrofitService {
    private static Retrofit retrofit;

    public static Retrofit getRetrofit() {
        if(retrofit != null)
            return retrofit;
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor());
        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl("http://192.168.1.4/ChallengifierAPI/api/")
                        .addConverterFactory(
                                GsonConverterFactory.create()
                        );
        Retrofit retrofit = builder.client(httpClient.build()).build();


        return retrofit;
    }
}
