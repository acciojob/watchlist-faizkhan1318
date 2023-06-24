package com.driver;

public class Movie {
    private String movieName;
    private int durationInMinutes;
    private double imdbRating;

    public Movie(){

    }
    public Movie(String name, int durationInMinutes, double imdbRating){
        this.movieName =name;
        this.durationInMinutes=durationInMinutes;
        this.imdbRating=imdbRating;
    }
    public double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(double imdbRating) {
        this.imdbRating = imdbRating;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }
}
