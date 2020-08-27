package com.example.moviesapp.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.moviesapp.R;
import com.example.moviesapp.utils.JsonMainAdapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class MovieInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);

        pullData();
    }

    private void pullData() {
        Intent intent = getIntent();
        if (intent != null) {
            String url = intent.getStringExtra("json_url");
            String id = intent.getStringExtra("json_id");
            String apiKey = intent.getStringExtra("json_api_key");

            JsonMainAdapter jsonMainAdapter = new JsonMainAdapter(url, id, apiKey
                    , MovieInfoActivity.this);

            jsonMainAdapter.moreInfoMovie();

        }
    }

}