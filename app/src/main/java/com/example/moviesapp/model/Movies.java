package com.example.moviesapp.model;

public class Movies {
    //http://www.omdbapi.com/?apikey=87d17a18&s=Re:zero
    //http://www.omdbapi.com/?apikey=87d17a18&i=tt5607616
    private String title;
    private String posterUrlPath;
    private String year;
    private String type;

    public Movies() {

    }

    public Movies(String title, String posterUrlPath, String year, String type) {
        this.title = title;
        this.posterUrlPath = posterUrlPath;
        this.year = year;
        this.type = type;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPosterUrlPath() {
        return posterUrlPath;
    }

    public void setPosterUrlPath(String posterUrlPath) {
        this.posterUrlPath = posterUrlPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
