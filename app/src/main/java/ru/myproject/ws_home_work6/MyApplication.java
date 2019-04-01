package ru.myproject.ws_home_work6;

import android.app.Application;

import ru.myproject.ws_home_work6.network.RestApi;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RestApi.init();
    }
}
