package ru.myproject.ws_home_work6.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import ru.myproject.ws_home_work6.R;
import ru.myproject.ws_home_work6.model.Movie;
import ru.myproject.ws_home_work6.ui.presenter.MovieListPresenter;

public class MovieItemFragment extends Fragment implements MovieListPresenter.ViewMovie {

    private ImageView image;
    private TextView title;
    private TextView plot;
    private TextView year;
    private TextView rate;
    private TextView awards;
    private TextView actors;
    private TextView website;
    private MovieListPresenter presenter;
    private ImageView editMovie, deleteMovie;
    Movie mMovie;
    Bundle bundle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_movie_description_item, container,
                false);
        image = v.findViewById(R.id.image_poster_movie);
        title = v.findViewById(R.id.title);
        plot = v.findViewById(R.id.text_description_movie);
        year = v.findViewById(R.id.year);
        rate = v.findViewById(R.id.text_rating);
        awards = v.findViewById(R.id.text_award);
        actors = v.findViewById(R.id.text_actors);
        website = v.findViewById(R.id.text_website);
        editMovie = v.findViewById(R.id.edit_movie);
        deleteMovie = v.findViewById(R.id.btn_delete_movie);
        bundle = getArguments();

        editMovie.setOnClickListener(v1 -> {


            EditAddMovieFragment editAddMovieFragment = EditAddMovieFragment.newInstance(mMovie);
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, editAddMovieFragment)
                    .addToBackStack(null)
                    .commit();
        });

        deleteMovie.setOnClickListener(v1 -> {
            presenter.deleteMovie(mMovie.getId());
            MovieListFragment movieListFragment = new MovieListFragment();
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, movieListFragment)
                    .commit();


        });

        return v;
    }

    public static MovieItemFragment newInstance(int id) {
        MovieItemFragment fragment = new MovieItemFragment();
        Bundle args = new Bundle();
        args.putInt("id", id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        bundle = getArguments();
        if (bundle != null) {
            presenter.loadItemMovieList(bundle.getInt("id"));
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        presenter = new MovieListPresenterImpl(this);
    }

    @Override
    public void addLoadedMovie(Movie movie) {

        title.setText(movie.getTitle());
        plot.setText(movie.getPlot());
        year.setText(String.valueOf(movie.getYear()));
        rate.setText(String.valueOf(movie.getRate()));
        awards.setText(movie.getAwards());
        actors.setText(movie.getActors());
        website.setText(movie.getWebsite());

        image.setImageDrawable(image.getContext().getDrawable(
                image.getContext().getResources().getIdentifier(
                        movie.getPoster(), "drawable", image.getContext().getPackageName())));

        mMovie = movie;
    }
}
