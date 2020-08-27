package com.example.moviesapp.utils;

public class JsonFields {

    public static final class JsonSearch {
        public static final String JsonArrayName = "Search";
        public static final String notFindImageMovie = "N/A";

        public static final String title = "Title";
        public static final String year = "Year";
        public static final String poster = "Poster";
        public static final String type = "Type";

    }

    public static final class Urls {
        public static final String searchUrl = "http://www.omdbapi.com/?apikey=%s&s=%s";
        public static final String infoMovieUrl =
                "http://www.omdbapi.com/?i=%s&apikey=%s";
        public static final String API_KEY="87d17a18";
    }

    public static final class infoMovie {

    }
}
