package com.example.moviesapp.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.moviesapp.R;
import com.example.moviesapp.data.DB.DatabaseHandler;
import com.example.moviesapp.model.UserInfo;

public class MainActivity extends AppCompatActivity {
    private static final String nullSearchEditText = "Введите название фильма";
    private EditText searchEditText;
    private Button searchButton;
    private Button sourceButton;
    private String nameSearch;
    private UserInfo userInfo;
    private DatabaseHandler databaseHandler ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchingFields();
        listenerClickedSearchButton();
        listenerClickedSourceButton();
    }

    private void searchingFields() {
        // поиск полей по id
        sourceButton = findViewById(R.id.sourceButton);
        searchButton = findViewById(R.id.searchButton);
        searchEditText = findViewById(R.id.searchEditText);
    }

    private void listenerClickedSearchButton() {
        // обоаботчик поисковой кнопки
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean fieldEditText = getInfoSearchInfo();
                if (fieldEditText) {
                    Intent recyclerViewIntent = new Intent(MainActivity.this,
                            RecyclerViewActivity.class);
                    // отправлем информацию в RecyclerActivity
                    activityPutValues(recyclerViewIntent, userInfo);
                } else {
                    Toast.makeText(MainActivity.this,nullSearchEditText,
                            Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void listenerClickedSourceButton() {
        sourceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,
                        SearchHistoryActivity.class);
                startActivity(intent);
            }
        });

    }

    private boolean getInfoSearchInfo() {
        nameSearch = String.valueOf(searchEditText.getText());
        if (nameSearch.length() > 0) {
            // еслм пользователь ввел что-то
            userInfo = new UserInfo(nameSearch);// создаем класс с информацией
            databaseHandler = new DatabaseHandler(getApplicationContext());
            databaseHandler.addUserInfo(userInfo);
            return true;
        } else {
            return false;
        }

    }

    private void activityPutValues(Intent intent, UserInfo userInfo) {
        intent.putExtra("nameSearch", userInfo.getNameSearch());

        startActivity(intent);
    }



}