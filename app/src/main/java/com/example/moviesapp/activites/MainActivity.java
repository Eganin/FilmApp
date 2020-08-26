package com.example.moviesapp.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moviesapp.R;

public class MainActivity extends AppCompatActivity {
    private static final String nullSearchEditText = "Введите название фильма";
    private EditText searchEditText;
    private Button searchButton;
    private Button sourceButton;
    private String nameSearch;
    private UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchingFields();
        listenerClickedSearchButton();
        listenerClickedSourceButton();
    }

    private void searchingFields() {
        sourceButton = findViewById(R.id.sourceButton);
        searchButton = findViewById(R.id.searchButton);
        searchEditText = findViewById(R.id.searchEditText);
    }

    private boolean getInfoSearchInfo() {
        nameSearch = String.valueOf(searchEditText.getText());
        if (nameSearch.length() > 0) {
            userInfo = new UserInfo(nameSearch);
            return true;
        } else {
            return false;
        }

    }

    private void activityPutValues(Intent intent, UserInfo userInfo) {
        intent.putExtra("nameSearch", userInfo.getNameSearch());

        startActivity(intent);
    }

    private void listenerClickedSearchButton() {
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean fieldEditText = getInfoSearchInfo();
                if (fieldEditText) {
                    Intent recyclerViewIntent = new Intent(MainActivity.this,
                            RecyclerViewActivity.class);
                    activityPutValues(recyclerViewIntent, userInfo);
                } else {
                    Toast.makeText(MainActivity.this,nullSearchEditText,
                            Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void listenerClickedSourceButton() {

    }


}