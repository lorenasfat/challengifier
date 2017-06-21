package com.example.lorena.challengifier.services.business.services;

import com.example.lorena.challengifier.models.LoginUser;
import com.example.lorena.challengifier.services.external.services.services.ApiLoginService;
import com.example.lorena.challengifier.utils.session.SessionUser;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Lorena on 02.05.2017.
 */

public class LoginService {
    private static String _username;
    private static String _password;
    public static boolean login(final String userName, String password){
        _username = userName;
        _password = password;
        com.example.lorena.challengifier.services.external.services.retrofit.interfaces.LoginService service = ApiLoginService.getService();
        LoginUser loginUser = new LoginUser();
        loginUser.setPassword(password);
        loginUser.setEmail(userName);
        Call<ResponseBody> call = service.login(loginUser);
        boolean isLoggedIn = false;
        try {
            Response<ResponseBody> response = call.execute();
            String responseContent = response.body().string();

            if(response.code()==200)
                SessionUser.authToken = responseContent;//get token
            else
                SessionUser.authToken ="-1";

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
