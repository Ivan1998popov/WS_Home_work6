package ru.myproject.ws_home_work6.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ru.myproject.ws_home_work6.R;
import ru.myproject.ws_home_work6.model.Movie;
import ru.myproject.ws_home_work6.ui.fragments.MovieListFragment;
import ru.myproject.ws_home_work6.utils.LoginController;


public class InfoActivity extends AppCompatActivity implements MovieListFragment.OnFragmentInteractionListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        MovieListFragment movieListFragment =
                MovieListFragment.newInstance(LoginController.getOwrInstance().getLogin());
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, movieListFragment)
                .commit();
    }


    @Override
    public void toastMovieTitle(Movie movie) {

    }
}
