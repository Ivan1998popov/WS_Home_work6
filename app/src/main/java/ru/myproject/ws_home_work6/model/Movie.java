package ru.myproject.ws_home_work6.model;

import com.google.gson.annotations.JsonAdapter;

import java.io.Serializable;



public class Movie implements Serializable {


    private String title;
    private int year;
    private Double rate;
    private String plot;
    private String awards;
    private String actors;
    private String website;
    private String poster;


    private int id;

    public Movie(){

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id +1;
    }
    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

//    public static Comparator<Movie> sMovieComparator = new Comparator<Movie>() {
//
//        @Override
//        public int compare(Movie o1, Movie o2) {
//
//            Float rating_movie1 = o1.getRate();
//            Float rating_movie2 = o2.getRate();
//
//
//            return rating_movie2.compareTo(rating_movie1);
//        }
//    };


}
