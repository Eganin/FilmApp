package com.example.moviesapp.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.moviesapp.R;
import com.example.moviesapp.model.Movies;
import com.example.moviesapp.utils.JsonFields;
import com.example.moviesapp.utils.JsonMainAdapter;
import com.example.moviesapp.utils.JsonFields.Urls;


import java.util.ArrayList;

public class RecyclerViewActivity extends AppCompatActivity {
    private static final String keySearch = "nameSearch";
    private RecyclerView recyclerView;
    private ArrayList<Movies> movies;
    private RequestQueue requestQueue;// объект для работы с JSON
    private String urlUsers;

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
        urlUsers = String.format(Urls.searchUrl, Urls.API_KEY,data);
        return data;
    }

    public void newInfoMovie(View view){
        JsonMainAdapter jsonMainAdapter = new JsonMainAdapter();
        String[] arrayInfoMovie = jsonMainAdapter.moreInfoMovie();
        activityNext();

    }

    private void activityNext(){
        Intent recyclerViewIntent = new Intent(RecyclerViewActivity.this,
                RecyclerViewActivity.class);
    }

    private void searchingFields() {

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        movies = new ArrayList<Movies>();
        requestQueue = Volley.newRequestQueue(this);

        getMovies();
    }

    private void getMovies() {
        JsonMainAdapter jsonMainAdapter = new JsonMainAdapter(urlUsers, movies,
                RecyclerViewActivity.this, recyclerView, "main");

        jsonMainAdapter.searchMovies();
    }

}