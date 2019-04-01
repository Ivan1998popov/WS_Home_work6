package ru.myproject.ws_home_work6.network;

import rx.Single;
import rx.functions.Func1;
import retrofit2.Response;

public  class SingleResponseFlatMap <T> implements Func1<Response <T>, Single<T>> {


    @Override
    public Single<T> call(Response<T> tResponse) {
       if(!tResponse.isSuccessful()){
           return Single.error(new RuntimeException(String.valueOf(tResponse.code())));
       }else{
           return Single.just(tResponse.body());
       }
    }
}
