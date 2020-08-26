package com.example.moviesapp.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.example.moviesapp.R;
import com.example.moviesapp.data.MovieAdapter;
import com.example.moviesapp.model.Movies;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private ArrayList<Movies> movies;
    private RequestQueue requestQueue;// объект для работы с JSON

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchingFields();

    }

    private void searchingFields() {
    }
}