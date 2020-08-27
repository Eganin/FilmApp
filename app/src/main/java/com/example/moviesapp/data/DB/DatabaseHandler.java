package com.example.moviesapp.data.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.moviesapp.model.UserInfo;
import com.example.moviesapp.utils.utilDB.Util;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    public DatabaseHandler(@Nullable Context context) {
        // create database
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_MOVIES_TABLE =
                String.format("CREATE TABLE %s (" +
                        "%s INTEGER PRIMARY KEY AUTOINCREMENT ," +
                        "%s TEXT UNIQUE" + ")", Util.TABLE_NAME, Util.KEY_ID, Util.KEY_TITLE);

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

    public List<UserInfo> getAllUserInfo() {
        SQLiteDatabase db = this.getReadableDatabase();

        List<UserInfo> userInfoList = new ArrayList<UserInfo>();

        String selectAllUserInfo = String.format("SELECT * FROM %s", Util.TABLE_NAME);

        Cursor cursor = db.rawQuery(selectAllUserInfo, null); // execute command

        if (cursor.moveToFirst()) {
            do {
                UserInfo userInfo = new UserInfo();
                userInfo.setId(cursor.getInt(0));
                userInfo.setNameSearch(cursor.getString(1));

                userInfoList.add(userInfo);

            } while (cursor.moveToNext());
        }

        db.close();

        return userInfoList;
    }

    public void addUserInfo(UserInfo userInfo) {
        SQLiteDatabase db = null;
        try {
            db = this.getWritableDatabase();

            ContentValues contentValues = new ContentValues();

            contentValues.put(Util.KEY_TITLE, userInfo.getNameSearch());

            db.insert(Util.TABLE_NAME, null, contentValues);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }


    }

    public UserInfo getUserInfo(int id) {
        SQLiteDatabase db = null;
        UserInfo userInfo = null;
        try {
            db = this.getReadableDatabase();

            Cursor cursor = db.query(Util.TABLE_NAME, new String[]{Util.KEY_ID, Util.KEY_TITLE},
                    Util.KEY_ID + "=?", new String[]{String.valueOf(id)},
                    null, null, null, null);

            int idUser = cursor.getInt(0);
            String nameSearch = cursor.getString(1);
            userInfo = new UserInfo(idUser, nameSearch);

            if (cursor != null) {
                cursor.moveToFirst();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
            return userInfo;
        }
    }

    public void deleteCar(UserInfo userInfo) {
        /*
        Удаление данных из БД
         */
        SQLiteDatabase db = this.getWritableDatabase();
        // удаление записи по id
        db.delete(Util.TABLE_NAME, Util.KEY_ID + "=?",
                new String[]{String.valueOf(userInfo.getId())});

        db.close();
    }

    public int updateCar(UserInfo userInfo) {// возвращает кол-во отредактрованных записей
        /*
        Всатвка данных в БД
         */
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(Util.KEY_TITLE, userInfo.getNameSearch());

        // обновление записи по id
        int result = db.update(Util.TABLE_NAME, contentValues, Util.KEY_ID + "=?",
                new String[]{String.valueOf(userInfo.getId())});
        db.close();

        return result;
    }
}
