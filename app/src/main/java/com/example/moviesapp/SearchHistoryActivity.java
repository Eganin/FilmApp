package com.example.moviesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.moviesapp.data.DB.DatabaseHandler;
import com.example.moviesapp.model.UserInfo;

import java.util.ArrayList;
import java.util.List;

public class SearchHistoryActivity extends AppCompatActivity {

    private DatabaseHandler databaseHandler;
    private List<UserInfo> allUsersInfo;
    private List<String> userIdList;
    private List<String> userNameSearchList;
    private ListView mainListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_history);

        getInfoSearchingFromDB();
        fillingListView();
    }

    private void getInfoSearchingFromDB() {
        databaseHandler = new DatabaseHandler(getApplicationContext());
        allUsersInfo = databaseHandler.getAllUserInfo();
        userIdList = new ArrayList<String>();
        userNameSearchList = new ArrayList<String>();
        for (UserInfo element : allUsersInfo) {
            userIdList.add(String.valueOf(element.getId()));
            userNameSearchList.add(element.getNameSearch());

        }
    }

    private void fillingListView() {
        mainListView = findViewById(R.id.mainListView);
        ArrayList<String> posts = new ArrayList<String>();

        for (int i = 0; i < userNameSearchList.size(); i++) {
            String result = userIdList.get(i)+"- " + userNameSearchList.get(i);
            posts.add(result);
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, posts);

        mainListView.setAdapter(arrayAdapter);
    }
}