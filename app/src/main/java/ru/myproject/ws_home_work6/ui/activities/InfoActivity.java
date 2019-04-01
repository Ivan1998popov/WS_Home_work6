package ru.myproject.ws_home_work6.ui.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import ru.myproject.ws_home_work6.R;
import ru.myproject.ws_home_work6.model.Movie;
import ru.myproject.ws_home_work6.ui.fragments.EditAddMovieFragment;
import ru.myproject.ws_home_work6.ui.fragments.MovieListFragment;

public class InfoActivity extends AppCompatActivity implements MovieListFragment.OnFragmentInteractionListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);



        MovieListFragment movieListFragment =new MovieListFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container,movieListFragment)
                .commit();
        FloatingActionButton newMovie = findViewById(R.id.fab);
        newMovie.setOnClickListener(v -> {

            EditAddMovieFragment editAddMovieFragment = new EditAddMovieFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, editAddMovieFragment)
                    .addToBackStack(null)
                    .commit();
        });
    }


    @Override
    public void toastMovieTitle(Movie movie) {

    }
}
