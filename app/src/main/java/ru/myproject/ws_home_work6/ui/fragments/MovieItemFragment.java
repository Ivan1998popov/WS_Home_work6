package ru.myproject.ws_home_work6.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import ru.myproject.ws_home_work6.R;
import ru.myproject.ws_home_work6.model.Movie;
import ru.myproject.ws_home_work6.ui.activities.InfoActivity;

public class MovieItemFragment extends Fragment {

    private ImageView image;
    private TextView title;
    private TextView plot;
    private TextView year;
    private TextView rate;
    private Integer id_item;
    private ImageView editMovie;

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
        editMovie=v.findViewById(R.id.edit_movie);

        Bundle bundle = getArguments();

        if (bundle != null) {

            Movie movie = (Movie) bundle.getSerializable("movie");
            assert movie != null;
            id_item = movie.getId();
            title.setText(movie.getTitle());
            plot.setText(movie.getPlot());
            year.setText(String.valueOf(movie.getYear()));
            rate.setText(String.valueOf(movie.getRate()));
            image.setImageDrawable(image.getContext().getDrawable(
                    image.getContext().getResources().getIdentifier(
                            movie.getPoster(), "drawable", image.getContext().getPackageName())));

            editMovie.setOnClickListener(v1 -> {
                InfoActivity activity =(InfoActivity)v.getContext();

                EditAddMovieFragment editAddMovieFragment = EditAddMovieFragment.newInstance(movie);
                activity.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, editAddMovieFragment)
                        .addToBackStack(null)
                        .commit();
            });
        }



        return v;
    }
    public static MovieItemFragment newInstance(Movie movie) {
        MovieItemFragment fragment = new MovieItemFragment();
        Bundle args = new Bundle();
        args.putSerializable("movie",movie);
        fragment.setArguments(args);
        return fragment;
    }



}
