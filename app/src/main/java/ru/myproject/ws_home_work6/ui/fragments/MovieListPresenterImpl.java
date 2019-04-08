package ru.myproject.ws_home_work6.ui.fragments;

import android.util.Log;

import java.util.ArrayList;


import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import ru.myproject.ws_home_work6.model.Movie;
import ru.myproject.ws_home_work6.network.RestApi;
import ru.myproject.ws_home_work6.network.SingleResponseFlatMap;
import ru.myproject.ws_home_work6.network.service.MovieService;

import ru.myproject.ws_home_work6.ui.presenter.MovieListPresenter;




class MovieListPresenterImpl implements MovieListPresenter {

    private  View view;
    private  ViewMovie mViewMovie;
    private MovieService mMovieService;

    public MovieListPresenterImpl(MovieListPresenter.View view){
        this.view =view;
        mMovieService= RestApi.createService(MovieService.class);
    }
    public MovieListPresenterImpl(){
        mMovieService= RestApi.createService(MovieService.class);
    }

    public MovieListPresenterImpl(MovieListPresenter.ViewMovie viewMovie) {
        this.mViewMovie=viewMovie;
        mMovieService=RestApi.createService(MovieService.class);
    }

    @Override
    public void loadItemMovieList(Integer id) {
        mMovieService.fetchMovie(id)
                .subscribeOn(Schedulers.io())
                .flatMap(new SingleResponseFlatMap<>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Movie>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Movie movie) {
                        mViewMovie.addLoadedMovie(movie);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    @Override
    public void loadMovieList() {

        mMovieService.fetchMovies()
                .subscribeOn(Schedulers.io())
                .flatMap( new SingleResponseFlatMap<>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ArrayList<Movie>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(ArrayList<Movie> movies) {
                        view.addLoadedItems(movies);
                    }

                    @Override
                    public void onError(Throwable error) {
                        Log.i("MyError",error.getMessage());
                        System.out.println(error.getMessage());
                        error.printStackTrace();
                    }
                });
    }

    @Override
    public void updateMovie(Movie movie) {
        mMovieService.updateMovie(movie)
                .subscribeOn(Schedulers.io())
                .flatMap(new SingleResponseFlatMap<>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Movie>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Movie movie) {
                        System.out.println("Обновление прошло успешно!");
                    }

                    @Override
                    public void onError(Throwable error) {
                        Log.i("MyError",error.getMessage());
                        System.out.println(error.getMessage());
                        error.printStackTrace();
                    }
                });
    }

    @Override
    public void createMovie(Movie movie) {
        mMovieService.createMovie(movie)
                .subscribeOn(Schedulers.io())
                .flatMap(new SingleResponseFlatMap<>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Movie>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Movie movie) {
                        System.out.println("Создание прошло успешно!");
                    }

                    @Override
                    public void onError(Throwable error) {
                        Log.i("MyError",error.getMessage());
                        System.out.println(error.getMessage());
                        error.printStackTrace();
                    }
                });
    }

    @Override
    public void deleteMovie(Integer id) {
        mMovieService.deleteMovie(id)
                .subscribeOn(Schedulers.io())
                .flatMap(new SingleResponseFlatMap<>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Boolean>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Boolean aBoolean) {
                        System.out.println("Создание прошло успешно!");
                    }

                    @Override
                    public void onError(Throwable error) {
                        Log.i("MyError",error.getMessage());
                        System.out.println(error.getMessage());
                        error.printStackTrace();
                    }
                });
    }


}
