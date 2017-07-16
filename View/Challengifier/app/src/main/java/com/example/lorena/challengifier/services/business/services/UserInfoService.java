package com.example.lorena.challengifier.services.business.services;

import com.example.lorena.challengifier.models.User;
import com.example.lorena.challengifier.services.external.services.services.ApiLoginService;
import com.example.lorena.challengifier.utils.session.SessionUser;

import retrofit2.Call;
import retrofit2.Response;

import static com.example.lorena.challengifier.activities.MainScreenActivity.updateDrawerContent;

/**
 * Created by Lorena on 26.06.2017.
 */

public class UserInfoService {

    public static User getInfo(String userName){
        com.example.lorena.challengifier.services.external.services.retrofit.interfaces.LoginService service = ApiLoginService.getService();

        Call<User> call = service.getInfo(userName);
        boolean isLoggedIn = false;
        try {
            Response<User> response = call.execute();
            User responseContent = (User)response.body();
            SessionUser.setLoggedInUser(responseContent);
            updateDrawerContent();
            return SessionUser.getLoggedInUser();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
