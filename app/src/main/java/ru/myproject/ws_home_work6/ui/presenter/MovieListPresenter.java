package ru.myproject.ws_home_work6.ui.presenter;

import java.util.ArrayList;

import ru.myproject.ws_home_work6.model.Movie;

public interface MovieListPresenter {

    void loadMovieList();

    void updateMovie(Movie movie);

    void createMovie(Movie movie);

    void deleteMovie(int id);

    interface View {
        void addLoadedItems(ArrayList<Movie> items);
    }
}
