package com.example.moviesapp.utils.utilDB;

import android.provider.BaseColumns;

public class Util implements BaseColumns {
    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "movie_DB";
    public static final String TABLE_NAME = "movies";

    public static final String KEY_ID = "id";
    public static final String KEY_TITLE = "title_search";

}
