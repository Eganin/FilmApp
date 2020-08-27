package com.example.moviesapp.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.moviesapp.R;
import com.example.moviesapp.data.InfoAdapter;
import com.example.moviesapp.data.MovieAdapter;
import com.example.moviesapp.utils.JsonMainAdapter;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MovieInfoActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<ArrayList<String>> resultList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);

        searchingFields();
        pullData();
    }

    private void pullData() {
        Intent intent = getIntent();
        if (intent != null) {
            String url = intent.getStringExtra("json_url");
            String id = intent.getStringExtra("json_id");
            String apiKey = intent.getStringExtra("json_api_key");

            JsonMainAdapter jsonMainAdapter = new JsonMainAdapter(url, id, apiKey
                    , MovieInfoActivity.this,recyclerView);

            jsonMainAdapter.moreInfoMovie();

        }
    }

    private void searchingFields() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        resultList = new ArrayList<>();
        InfoAdapter infoAdapter = new InfoAdapter(getApplicationContext(),resultList);
        recyclerView.setAdapter(infoAdapter);
    }

}