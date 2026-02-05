package com.example.streamingplatformfeedback.model;

public class Favorite {
    private int userId;
    private int movieId;
    private String title;
    private double rating;
    private String genre;

    public Favorite(int userId, int movieId, String title, double rating, String genre) {
        this.userId = userId;
        this.movieId = movieId;
        this.title = title;
        this.rating = rating;
        this.genre = genre;
    }


    public int getUserId(){
        return userId;
    }
    public void setUserID(int userId){
    this.userId = userId;
    }


    public int getMovieId(){
        return movieId;
    }
    public void setMovieId(int movieId){
        this.movieId = movieId;
    }


    public String getTitle() { return title; }
    public double getRating() { return rating; }
    public String getGenre() { return genre; }


    @Override
    public String toString(){
        return "User (" + userId + ") favorite Movies (" + movieId + ")";
    }
}
