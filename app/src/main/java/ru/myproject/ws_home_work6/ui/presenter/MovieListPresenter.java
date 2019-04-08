package ru.myproject.ws_home_work6.ui.presenter;

import java.util.ArrayList;

import ru.myproject.ws_home_work6.model.Movie;

public interface MovieListPresenter {


    void loadItemMovieList(Integer id);

    void loadMovieList();

    void updateMovie(Movie movie);

    void createMovie(Movie movie);

    void deleteMovie(Integer id);

    interface View {
        void addLoadedItems(ArrayList<Movie> items);
    }
    interface ViewMovie {
        void addLoadedMovie(Movie movie);
    }
}
