package ru.myproject.ws_home_work6.network.service;


import java.util.ArrayList;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import ru.myproject.ws_home_work6.model.Movie;


public interface MovieService {
    @GET("/api/movies/fetchMovies?sort=desc")
    Single<Response<ArrayList<Movie>>> fetchMovies();

    @GET("/api/movies/fetchMovie/{movieId}")
    Single<Response<Movie>> fetchMovie(@Path("movieId") int id);

    @PUT("/api/movies/updateMovie")
    Single<Response<Movie>> updateMovie(@Body Movie movie);


    @POST("/api/movies/createMovie")
    Single<Response<Movie>> createMovie(@Body Movie movie);

    @DELETE("/api/movies/deleteMovie/{movieId}")
    Single<Response<Boolean>> deleteMovie(@Path("movieId") int id);


}
