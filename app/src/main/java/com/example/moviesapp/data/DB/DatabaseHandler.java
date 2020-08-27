package com.example.moviesapp.data.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.moviesapp.utils.utilDB.Util;

public class DatabaseHandler extends SQLiteOpenHelper{
    public DatabaseHandler(@Nullable Context context){
        // create database
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_MOVIES_TABLE =
                String.format("CREATE TABLE %s ("+
                        "%s INTEGER PRIMARY KEY AUTOINCREMENT ,"+
                        "%s TEXT UNIQUE ,"+
                        "%s TEXT UNIQUE ,"+
                        "%s TEXT UNIQUE,"+
                        "%s TEXT ,"+
                        "%s TEXT "+")",Util.TABLE_NAME,Util.KEY_ID,Util.KEY_TITLE,
                        Util.KEY_POSTER_URL_PATH , Util.KEY_YEAR, Util.KEY_TYPE,
                        Util.KEY_IMB_ID);

        // create table
        sqLiteDatabase.execSQL(CREATE_MOVIES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        /*
        перезапись таблицы
         */
        sqLiteDatabase.execSQL(String.format("DROP TABLE IF EXISTS %s", Util.TABLE_NAME));
        onCreate(sqLiteDatabase);
    }
}
