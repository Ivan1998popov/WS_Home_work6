package ru.myproject.ws_home_work6.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import ru.myproject.ws_home_work6.R;
import ru.myproject.ws_home_work6.model.Movie;
import ru.myproject.ws_home_work6.ui.activities.InfoActivity;
import ru.myproject.ws_home_work6.ui.presenter.MovieListPresenter;

public class EditAddMovieFragment extends Fragment {


    EditText title;
    EditText plot;
    EditText rate;
    EditText year;
    Button editButton;
    View v;
    MovieListPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v =inflater.inflate(R.layout.fragment_edit_movie,container,false);
        title = v.findViewById(R.id.title);
        plot = v.findViewById(R.id.plot);
        year = v.findViewById(R.id.year);
        rate = v.findViewById(R.id.rate);
        editButton=v.findViewById(R.id.btn_edit);

        Bundle bundle =getArguments();
        if(bundle!=null){
            Movie movie = (Movie)bundle.getSerializable("movie");
            title.setText(movie.getTitle());
            plot.setText(movie.getPlot());
            year.setText(String.valueOf(movie.getYear()));
            rate.setText(String.valueOf(movie.getRate()));
            editButton.setOnClickListener(v1 -> {
                movie.setTitle(title.getText().toString());
                movie.setPlot(plot.getText().toString());
                movie.setYear(Integer.parseInt(year.getText().toString()));
                movie.setRate(Double.parseDouble(rate.getText().toString()));
                presenter.updateMovie(movie);
//                InfoActivity activity =(InfoActivity)v.getContext();
//                MovieListFragment movieListFragment =new MovieListFragment();
//                FragmentManager fragmentManager =activity.getSupportFragmentManager();
//                fragmentManager.beginTransaction()
//                        .replace(R.id.fragment_container,movieListFragment)
//                        .commit();
//                clearStack(fragmentManager);
            });
        }else{
            Movie movie =new Movie();
            movie.setActors(null);
            movie.setAwards(null);
            movie.setWebsite(null);
            movie.setId(null);
         //   System.out.println(movie.getId());
            movie.setPoster("movie1");
            editButton.setOnClickListener(v1 -> {
                movie.setTitle(title.getText().toString());
                movie.setPlot(plot.getText().toString());
                movie.setYear(Integer.parseInt(year.getText().toString()));
                movie.setRate(Double.parseDouble(rate.getText().toString()));
                presenter.createMovie(movie);
//                InfoActivity activity = (InfoActivity) v.getContext();
//                MovieListFragment movieListFragment = new MovieListFragment();
//                FragmentManager fragmentManager = activity.getSupportFragmentManager();
//                fragmentManager.beginTransaction()
//                        .replace(R.id.fragment_container, movieListFragment)
//                        .commit();
//
//                clearStack(fragmentManager);
            });
        }

        return v;
    }
    private void clearStack(FragmentManager fragmentManager){
        int count = fragmentManager.getBackStackEntryCount();
        while(count > 0){
            fragmentManager.popBackStack();
            count--;
        }
    }
    public static EditAddMovieFragment newInstance(Movie movie) {
        EditAddMovieFragment fragment = new EditAddMovieFragment();
        Bundle args = new Bundle();
        args.putSerializable("movie",movie);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        presenter = new MovieListPresenterImpl();
    }
}
