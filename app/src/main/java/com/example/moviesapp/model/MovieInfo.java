package com.example.moviesapp.model;

public class MovieInfo {
    private String title;
    private String year;
    private String rated;
    private String runtime;
    private String released;
    private String genre;
    private String actors;
    private String plot;
    private String rating1;
    private String rating2;
    private String rating3;
    private String rating4;
    private String rating5;

    public MovieInfo() {
    }

    public MovieInfo(String title, String year, String rated, String runtime,
                     String released, String genre, String actors,
                     String plot, String rating1, String rating2,
                     String rating3, String rating4, String rating5) {
        this.title = title;
        this.year = year;
        this.rated = rated;
        this.runtime = runtime;
        this.released = released;
        this.genre = genre;
        this.actors = actors;
        this.plot = plot;
        this.rating1 = rating1;
        this.rating2 = rating2;
        this.rating3 = rating3;
        this.rating4 = rating4;
        this.rating5 = rating5;

    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getRating5() {
        return rating5;
    }

    public void setRating5(String rating5) {
        this.rating5 = rating5;
    }

    public String getRating4() {
        return rating4;
    }

    public void setRating4(String rating4) {
        this.rating4 = rating4;
    }

    public String getRating3() {
        return rating3;
    }

    public void setRating3(String rating3) {
        this.rating3 = rating3;
    }

    public String getRating2() {
        return rating2;
    }

    public void setRating2(String rating2) {
        this.rating2 = rating2;
    }

    public String getRating1() {
        return rating1;
    }

    public void setRating1(String rating1) {
        this.rating1 = rating1;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}

