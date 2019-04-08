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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

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
    EditText awards;
    EditText actors;
    EditText website;
    CheckBox is_website;
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
        awards = v.findViewById(R.id.text_awards);
        actors = v.findViewById(R.id.text_actors);
        website = v.findViewById(R.id.text_website);
        is_website=v.findViewById(R.id.checkBox);
        editButton=v.findViewById(R.id.btn_edit);

        Bundle bundle =getArguments();
        if(bundle!=null){
            Movie movie = (Movie)bundle.getSerializable("movie");
            title.setText(movie.getTitle());
            plot.setText(movie.getPlot());
            awards.setText(movie.getAwards());
            actors.setText(movie.getActors());
            website.setText(movie.getWebsite());
            year.setText(String.valueOf(movie.getYear()));
            rate.setText(String.valueOf(movie.getRate()));

            if(website.getText().toString().equals("N/A")){
                website.setVisibility(View.GONE);
            }else{
                is_website.setChecked(true);
            }

            editButton.setOnClickListener(v1 -> {
                movie.setTitle(title.getText().toString());
                movie.setPlot(plot.getText().toString());
                movie.setAwards(awards.getText().toString());
                movie.setActors(actors.getText().toString());
                movie.setWebsite(website.getText().toString());
                movie.setYear(Integer.parseInt(year.getText().toString()));
                movie.setRate(Double.parseDouble(rate.getText().toString()));
                if(check_year(year)&&check_rate(rate))
                presenter.updateMovie(movie);
                MovieListFragment movieListFragment =new MovieListFragment();
                FragmentManager fragmentManager =getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container,movieListFragment)
                        .commit();
                clearStack(fragmentManager);
            });
        }else{
            Movie movie =new Movie();
            movie.setId(null);

            movie.setPoster("movie1");
            editButton.setOnClickListener(v1 -> {
                movie.setTitle(title.getText().toString());
                movie.setPlot(plot.getText().toString());
                movie.setYear(Integer.parseInt(year.getText().toString()));
                movie.setRate(Double.parseDouble(rate.getText().toString()));
                movie.setActors(actors.getText().toString());
                movie.setAwards(awards.getText().toString());

                if(check_year(year)&&check_rate(rate))
                presenter.createMovie(movie);
                MovieListFragment movieListFragment = new MovieListFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, movieListFragment)
                        .commit();
                clearStack(fragmentManager);
            });
        }
        is_website.setOnClickListener(v1 -> {
            if(is_website.isChecked()){
                website.setVisibility(View.VISIBLE);
            }else{
                website.setVisibility(View.GONE);
            }
        });


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

    boolean check_rate(EditText rate){
        double number=Double.parseDouble(rate.getText().toString());
        if(0<number&&number<10){
            return true;
        }
        Toast.makeText(getContext(),"Некорректно введен рейтинг фильма!",Toast.LENGTH_SHORT).show();
        System.out.println(number);
        return false;
    }
    boolean check_year(EditText year){
        int number=Integer.parseInt(year.getText().toString());
        if(2100>number&&number>1850){
           return true;
        }else{

            Toast.makeText(getContext(),"Некорректно введена дата выхода фильма!",Toast.LENGTH_SHORT).show();
            return false;
        }
    }

}
