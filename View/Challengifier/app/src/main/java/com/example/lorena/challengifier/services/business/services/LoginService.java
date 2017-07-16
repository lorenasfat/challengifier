package com.example.lorena.challengifier.services.business.services;

import android.app.Activity;

import com.example.lorena.challengifier.models.LoginUser;
import com.example.lorena.challengifier.models.User;
import com.example.lorena.challengifier.services.external.services.services.ApiLoginService;
import com.example.lorena.challengifier.utils.session.SessionUser;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

import static com.example.lorena.challengifier.utils.session.SessionUser.authToken;

/**
 * Created by Lorena on 02.05.2017.
 */

public class LoginService {
    private static String _username;
    private static String _password;

    public static boolean login(Activity activity, final String userName, String password) {
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

            if (response.code() == 200) {
                authToken = responseContent;//get token
                //make call for info
                User user = UserInfoService.getInfo(userName);
                SessionUser.setLoggedInUser(user);
                SessionUser.saveSession(activity, authToken, user);
            } else {
                authToken = "-1";
                SessionUser.clearSession(activity);
            }

        } catch (Exception e) {
            authToken = "-1";
            e.printStackTrace();
        }
        if (authToken.equalsIgnoreCase("-1"))
            return false;
        SessionUser.currentUserId = _username;
        return true;
    }
}
