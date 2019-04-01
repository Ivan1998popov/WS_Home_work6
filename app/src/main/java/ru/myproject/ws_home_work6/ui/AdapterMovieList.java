package ru.myproject.ws_home_work6.ui;


import android.support.annotation.NonNull;

import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

import ru.myproject.ws_home_work6.R;
import ru.myproject.ws_home_work6.model.Movie;
import ru.myproject.ws_home_work6.ui.activities.InfoActivity;
import ru.myproject.ws_home_work6.ui.fragments.MovieItemFragment;
import ru.myproject.ws_home_work6.ui.fragments.MovieListFragment;
import ru.myproject.ws_home_work6.ui.presenter.MovieListPresenter;


public class AdapterMovieList extends RecyclerView.Adapter<AdapterMovieList.MyViewHolder> {

    private ArrayList<Movie> items;
    private MovieListFragment fragment;
    MovieListPresenter presenter;

    public AdapterMovieList(MovieListFragment fragment,MovieListPresenter presenter) {

        this.fragment = fragment;
        items = new ArrayList<>();
        this.presenter=presenter;
    }

    public void loadItems(ArrayList<Movie> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView title;
        private TextView plot;
        private TextView year;
        private TextView rate;
        private ImageView btn_delete_movie;

        public MyViewHolder(View v) {
            super(v);
            image = v.findViewById(R.id.image_poster_movie);
            title = v.findViewById(R.id.title);
            plot = v.findViewById(R.id.text_description_movie);
            year = v.findViewById(R.id.year);
            rate = v.findViewById(R.id.text_rating);
            btn_delete_movie=v.findViewById(R.id.btn_delete_movie);

        }

    }

    @NonNull
    @Override
    public AdapterMovieList.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie_recyclerview,
                viewGroup, false);

        final MyViewHolder myViewHolder = new MyViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = myViewHolder.getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Movie movie = items.get(position);
                    Log.d("msg", movie.getTitle());


                    InfoActivity activity =(InfoActivity)v.getContext();

                    MovieItemFragment movieItemFragment = MovieItemFragment.newInstance(movie);
                    activity.getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, movieItemFragment)
                            .addToBackStack(null)
                            .commit();


                }
            }
        });

        return myViewHolder;
    }

    public void removeItem(int position) {
        items.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMovieList.MyViewHolder holder, int i) {
        holder.title.setText(items.get(i).getTitle());
        holder.plot.setText(items.get(i).getPlot());
        holder.year.setText(String.valueOf(items.get(i).getYear()));
        holder.rate.setText(String.valueOf(items.get(i).getRate()));
        holder.image.setImageDrawable(holder.image.getContext().getDrawable(
                holder.image.getContext().getResources().getIdentifier(
                        items.get(i).getPoster(), "drawable", holder.image.getContext().getPackageName())));


        holder.btn_delete_movie.setOnClickListener(v1 -> {
            presenter.deleteMovie(items.get(i).getId());
            removeItem(i);
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
