package ru.myproject.ws_home_work6.utils;

import android.app.Application;
import android.content.Intent;


import ru.myproject.ws_home_work6.model.TokenResponse;
import ru.myproject.ws_home_work6.network.RestApi;
import ru.myproject.ws_home_work6.network.service.AuthService;
import ru.myproject.ws_home_work6.ui.activities.LoginActivity;


public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Prefs.init(this);
        RestApi.init((route, response) -> {

            String refreshToken=Prefs.get().getString(Prefs.Keys.RefreshToken.toString(),
                    null);
            if(refreshToken!=null){
                LoginController.getOwrInstance().setAccessToken(null);
                TokenResponse tokenResponse =RestApi.createService(AuthService.class)
                        .refreshToken(refreshToken,"refresh_token")
                        .execute().body();
                if(tokenResponse!=null){
                    LoginController.getOwrInstance().setAccessToken(tokenResponse.getAccessToken());
                    LoginController.getOwrInstance().setRefreshToken(tokenResponse.getRefreshToken());
                    return response.request().newBuilder()
                            .addHeader(AuthService.AUTHORIZATION_HEADER,
                                    AuthService.TOKEN_PREFIX+
                                            tokenResponse.getAccessToken()).build();
                }else {
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }else{
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
            return null;
        });
    }
}
