package com.example.moviesapp.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.moviesapp.R;
import com.example.moviesapp.data.MovieAdapter;
import com.example.moviesapp.model.Movies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RecyclerViewActivity extends AppCompatActivity {
    private static final String keySearch = "nameSearch";
    private static final String notFindImageMovie = "N/A";
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
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
        Intent receivedOrderIntent = getIntent();
        // получение отправленных данных
        String data = receivedOrderIntent.getStringExtra(keySend);
        urlUsers = String.format("http://www.omdbapi.com/?apikey=87d17a18&s=%s", data);
        return data;
    }

    private void searchingFields() {

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        movies = new ArrayList<Movies>();
        requestQueue = Volley.newRequestQueue(this);

        getMovies();
    }

    private void getMovies() {
        /*
        Метод отвечает за работу с JSON
         */
        final String url = urlUsers;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,// GET запрос
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                /*
                парсинг JSON
                 */

                try {
                    // получаем JSON array
                    JSONArray jsonArray = response.getJSONArray("Search");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        // получаем по индексу jsonobject
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        // извлекаем данные по ключу
                        String title = jsonObject.getString("Title");
                        String year = jsonObject.getString("Year");
                        // получаем ссылку на изображение и проверяем ее валидность
                        String urlPath = preparingImage(jsonObject.getString("Poster"));
                        String type = jsonObject.getString("Type");

                        Movies moviesObject = new Movies();

                        moviesObject.setTitle(title);
                        moviesObject.setYear(year);
                        moviesObject.setPosterUrlPath(urlPath);
                        moviesObject.setType(type);

                        movies.add(moviesObject);
                    }

                    movieAdapter = new MovieAdapter(RecyclerViewActivity.this,
                            movies);
                    recyclerView.setAdapter(movieAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                /*
                Метод отвечающий за обработку ошибок
                 */
                Toast.makeText(getApplicationContext(),
                        "Произошла ошибка попробуйте еще", Toast.LENGTH_LONG);
                error.printStackTrace();


            }
        });
        requestQueue.add(request);// добавляем в очередь запросов
    }

    private String preparingImage(String imageUrl) {
        String result;
        if (imageUrl.equals(notFindImageMovie)) {
            result = notFindImageMovie;
            return result;
        } else {
            result = imageUrl;
            return result;
        }
    }
}