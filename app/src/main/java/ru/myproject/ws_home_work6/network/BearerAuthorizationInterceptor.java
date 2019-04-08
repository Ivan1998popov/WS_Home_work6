package ru.myproject.ws_home_work6.network;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import ru.myproject.ws_home_work6.network.service.AuthService;
import ru.myproject.ws_home_work6.utils.Prefs;


public class BearerAuthorizationInterceptor implements Interceptor {

    private static final String BASIC_TOKEN = "bXktY2xpZW50Om15LXNlY3JldA==";
    @Override
    public Response intercept(Chain chain) throws IOException {

        String token = Prefs.get().getString(Prefs.Keys.Token.toString(),null);

        Request.Builder builder =chain.request().newBuilder();
        if (chain.request().headers().get(AuthService.AUTHORIZATION_HEADER) == null){
            if (chain.request().url().encodedPath().contains("oauth/")){
                builder.addHeader(AuthService.AUTHORIZATION_HEADER,
                        AuthService.TOKEN_PREFIX_BASIC + BASIC_TOKEN);
            } else if (chain.request().url().encodedPath().contains("auth/")){

            }else{
                builder.addHeader(AuthService.AUTHORIZATION_HEADER,
                        AuthService.TOKEN_PREFIX + token);
            }
        }


        return chain.proceed(builder.build());
    }
}
