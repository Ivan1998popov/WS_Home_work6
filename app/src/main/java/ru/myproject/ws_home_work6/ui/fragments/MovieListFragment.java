package ru.myproject.ws_home_work6.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ru.myproject.ws_home_work6.R;
import ru.myproject.ws_home_work6.model.Movie;
import ru.myproject.ws_home_work6.ui.AdapterMovieList;
import ru.myproject.ws_home_work6.ui.activities.InfoActivity;
import ru.myproject.ws_home_work6.ui.activities.LoginActivity;
import ru.myproject.ws_home_work6.ui.presenter.MovieListPresenter;
import ru.myproject.ws_home_work6.utils.LoginController;

public class MovieListFragment extends Fragment implements MovieListPresenter.View {

    RecyclerView mRecyclerView;
    public AdapterMovieList adapter;
    MovieListPresenter presenter;
    private static final String ARG_LOGIN = "LOGIN";
    private String login;
    private SwipeRefreshLayout swipeRefreshLayout;
    private OnFragmentInteractionListener listener;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            login = getArguments().getString(ARG_LOGIN);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);

//        Intent intent = new Intent(getActivity(), LoginActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);

        FloatingActionButton newMovie = view.findViewById(R.id.fab);
        newMovie.setOnClickListener(v -> {


            EditAddMovieFragment editAddMovieFragment = new EditAddMovieFragment();
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, editAddMovieFragment)
                    .addToBackStack(null)
                    .commit();
        });

        return view;
    }


    public static MovieListFragment newInstance(String login) {
        MovieListFragment fragment = new MovieListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_LOGIN, login);
        fragment.setArguments(args);
        return fragment;
    }




    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = view.findViewById(R.id.movie_list_items);
        adapter = new AdapterMovieList(this, presenter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(adapter);

//        swipeRefreshLayout = view.findViewById(R.id.movieSwipe);
//        swipeRefreshLayout.setOnRefreshListener(() -> presenter.loadMovieList());

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
        presenter = new MovieListPresenterImpl(this);
    }


    public void showMovieToast(Movie movie) {
        listener.toastMovieTitle(movie);
    }


    public void dismissLoadingDialog() {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.loadMovieList();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        adapter = null;
        swipeRefreshLayout = null;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }


    @Override
    public void addLoadedItems(ArrayList<Movie> items) {
        if (adapter != null)
            adapter.loadItems(items);

    }

    public interface OnFragmentInteractionListener {
        void toastMovieTitle(Movie movie);
    }
}
