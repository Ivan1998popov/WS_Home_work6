package ru.myproject.ws_home_work6.network;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApi {


    public  static  String BASE_URL ="http://192.168.1.19:8080/";

    private static Retrofit sRetrofit=null;

    public  static  void init (){
        OkHttpClient.Builder okHttpClient =new OkHttpClient.Builder();


        HttpLoggingInterceptor interceptor =new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient.addInterceptor(interceptor);

        Gson gson =new GsonBuilder().create();
        sRetrofit=new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .client(okHttpClient.build())
                .build();


    }

    public static  <T> T createService (Class <T> serviceClass){
        if(sRetrofit==null){
            throw  new IllegalStateException("Call `RestApi.init(Context, Authenticator)` before calling this method.");
        }
        return sRetrofit.create(serviceClass);
    }

}
