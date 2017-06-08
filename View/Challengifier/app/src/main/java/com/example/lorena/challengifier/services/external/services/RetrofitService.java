package com.example.lorena.challengifier.services.external.services;

import com.example.lorena.challengifier.utils.session.SessionUser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Lorena on 09.01.2017.
 */

public class RetrofitService {
    private static Retrofit retrofit;
    private static String baseUrl="http://192.168.1.6/ChallengifierAPI/api/";//"10.132.2.140";

    public static Retrofit getRetrofit() {
        if(retrofit != null)
            return retrofit;
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor()).addInterceptor(
                new Interceptor() {
                    @Override
                    public Response intercept(Interceptor.Chain chain) throws IOException {
                        Request original = chain.request();
                        String completeToken = "Basic "+ SessionUser.authToken;
                        // Request customization: add request headers
                        Request.Builder requestBuilder = original.newBuilder()
                                .header("Authorization", completeToken); // <-- this is the important line

                        Request request = requestBuilder.build();
                        return chain.proceed(request);
                    }
                }
        );

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .addConverterFactory(
                                GsonConverterFactory.create(gson)
                        );
        Retrofit retrofit = builder.client(httpClient.build()).build();


        return retrofit;
    }

    public static Retrofit getBasicRetrofit() {
        Gson gson = new Gson();
        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .addConverterFactory(
                                GsonConverterFactory.create(gson)
                        );
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit;
    }

}
