package ru.myproject.ws_home_work6.network.service;

import com.google.gson.annotations.JsonAdapter;

import java.util.ArrayList;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import ru.myproject.ws_home_work6.model.Movie;
import rx.Single;

public interface MovieService {
    @GET("fetchMovies?sort=desc")
    Single<Response<ArrayList<Movie>>> fetchMovies();

    @PUT("updateMovie")
    Single<Response<Movie>> updateMovie(@Body Movie movie);


    @POST("createMovie")
    Single<Response<Movie>> createMovie(@Body Movie movie);

    @DELETE("deleteMovie/{movieId}")
    Single<Response<Boolean>> deleteMovie(@Path("movieId") int id);


}
