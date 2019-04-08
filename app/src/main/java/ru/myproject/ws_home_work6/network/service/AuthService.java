package ru.myproject.ws_home_work6.network.service;

import android.os.AsyncTask;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import ru.myproject.ws_home_work6.model.Registration;
import ru.myproject.ws_home_work6.model.StatusRegistration;
import ru.myproject.ws_home_work6.model.TokenResponse;


public interface AuthService  {

    String AUTHORIZATION_HEADER="Authorization";
    String TOKEN_PREFIX="Bearer ";
    String TOKEN_PREFIX_BASIC="Basic ";



    @FormUrlEncoded
    @POST("oauth/token")
    Single<Response<TokenResponse>> singInByPassword(@Header("Authorization") String header,
                                                     @Field("username") String username,
                                                     @Field("password") String password,
                                                     @Field("grant_type") String grantType);



    @POST("auth/register")
    Single<Response<StatusRegistration>> registerIn(@Body Registration registration);


    Call<TokenResponse> refreshToken(@Field("refresh_token") String refreshToken,
                                     @Field("grant_type") String grantType);


}
