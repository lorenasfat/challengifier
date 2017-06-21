package com.example.lorena.challengifier.services.business.services;

import com.example.lorena.challengifier.models.SignupUser;
import com.example.lorena.challengifier.services.external.services.services.ApiSignupService;
import com.example.lorena.challengifier.utils.session.SessionUser;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Lorena on 03.05.2017.
 */

public class SignupService {
    private static String _username;
    private static String _password;
    public static boolean signup(final String userName, String password){
        _username = userName;
        _password = password;
        com.example.lorena.challengifier.services.external.services.retrofit.interfaces.SignupService service = ApiSignupService.getService();
        SignupUser signupUser = new SignupUser();
        signupUser.setPassword(password);
        signupUser.setEmail(userName);
        Call<ResponseBody> call = service.register(signupUser);
        boolean isLoggedIn = false;
        try {
            Response<ResponseBody> response = call.execute();
            String responseContent = response.body().string();

            if(response.code()==200)
                SessionUser.authToken =responseContent;//get token
            else
                SessionUser.authToken = "-1";

        } catch (Exception e) {
            SessionUser.authToken = "-1";
            e.printStackTrace();
        }
        if(SessionUser.authToken.equalsIgnoreCase("-1"))
            return false;
        SessionUser.currentUser = _username;
        return true;
    }
}
