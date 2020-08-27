package com.example.moviesapp.utils;

import com.example.moviesapp.R;

public class JsonFields {

    public static final class JsonSearch {
        public static final String JsonArrayName = "Search";
        public static final String notFindImageMovie = "N/A";
        public static final int defaultPathImage = R.drawable.ic_launcher_foreground;

        public static final String title = "Title";
        public static final String year = "Year";
        public static final String poster = "Poster";
        public static final String type = "Type";
        public static final  String imdbID="imdbID";

    }

    public static final class Urls {
        public static final String searchUrl = "http://www.omdbapi.com/?apikey=%s&s=%s";
        public static final String infoMovieUrl =
                "http://www.omdbapi.com/?i=%s&apikey=%s";
        public static final String API_KEY="87d17a18";
    }

    public static final class infoMovie {
        public static final String title = "Title";
        public static final String year = "Year";
        public static final String rated = "Rated";
        public static final String runtime ="Runtime";
        public static final String released ="Released";
        public static final String genre ="Genre";
        public static final String actors ="Actors";
        public static final String plot = "Plot";
        public static final String imdbRating = "imdbRating";
    }
}
