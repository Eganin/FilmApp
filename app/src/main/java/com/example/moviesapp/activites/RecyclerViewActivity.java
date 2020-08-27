package com.example.moviesapp.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.moviesapp.R;
import com.example.moviesapp.data.MovieAdapter;
import com.example.moviesapp.model.MovieInfo;
import com.example.moviesapp.model.Movies;
import com.example.moviesapp.model.UserInfo;
import com.example.moviesapp.utils.JsonMainAdapter;
import com.example.moviesapp.utils.JsonFields.Urls;


import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class RecyclerViewActivity extends AppCompatActivity {
    private static final String keySearch = "nameSearch";
    private RecyclerView recyclerView;
    private ArrayList<Movies> movies;
    private RequestQueue requestQueue;// объект для работы с JSON
    private String urlUsers;
    private String urlInfoMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        String dataName = receivingDataIntentString(keySearch);
        setTitle(dataName);
        searchingFields();

    }

    public String receivingDataIntentString(String keySend) {
        /*
        Метод отвесает за получение данных из MainActivity
         */
        Intent receivedOrderIntent = getIntent();
        // получение отправленных данных
        String data = receivedOrderIntent.getStringExtra(keySend);
        urlUsers = String.format(Urls.searchUrl, Urls.API_KEY, data);
        return data;
    }


    private void searchingFields() {

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        movies = new ArrayList<Movies>();
        requestQueue = Volley.newRequestQueue(this);

        MovieAdapter movieAdapter = new MovieAdapter(getApplicationContext(), movies);
        recyclerView.setAdapter(movieAdapter);
        getMovies();
    }

    private void getMovies() {
        JsonMainAdapter jsonMainAdapter = new JsonMainAdapter(urlUsers, movies,
                RecyclerViewActivity.this, recyclerView, "main");

        MovieAdapter.cleanImbMainId();
        jsonMainAdapter.searchMovies();
    }

}