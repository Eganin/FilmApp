package com.example.moviesapp.utils;

import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.moviesapp.R;
import com.example.moviesapp.activites.MovieInfoActivity;
import com.example.moviesapp.activites.RecyclerViewActivity;
import com.example.moviesapp.data.InfoAdapter;
import com.example.moviesapp.data.MovieAdapter;
import com.example.moviesapp.model.Movies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.Arrays;

import com.example.moviesapp.utils.JsonFields.JsonSearch;
import com.example.moviesapp.utils.JsonFields.infoMovie;
import com.squareup.picasso.Picasso;


public class JsonMainAdapter {
    private String urlUsers;
    private String urlInfoMovie;
    private String id;
    private String mode;
    private ArrayList<Movies> movies;
    private RequestQueue requestQueue;// объект для работы с JSON
    private RecyclerViewActivity activity;
    private MovieInfoActivity infoActivity;
    private MovieAdapter movieAdapter;
    private RecyclerView recyclerView;
    public static ArrayList<String> listJsonRes= new ArrayList<String>();

    public JsonMainAdapter(String urlUsers, ArrayList<Movies> movies, RecyclerViewActivity activty,
                           RecyclerView recyclerView, String mode) {
        // constructor  для общего поиска Json
        this.urlUsers = urlUsers;
        this.mode = mode;
        this.movies = movies;
        this.activity = activty;
        requestQueue = Volley.newRequestQueue(activty);
        this.recyclerView = recyclerView;
    }

    public JsonMainAdapter(String urlInfoMovie, String id, String apiKey,
                           MovieInfoActivity activity,RecyclerView recyclerView) {
        // constructor для поиска информации по фильму
        this.id = id;
        this.urlInfoMovie = String.format(urlInfoMovie, id, apiKey);
        this.recyclerView=recyclerView;
        requestQueue = Volley.newRequestQueue(activity);

    }


    public void searchMovies() {
        /*
        Метод отвечает за работу с JSON
         */
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,// GET запрос
                urlUsers, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                /*
                парсинг JSON
                 */
                try {
                    // получаем JSON array
                    JSONArray jsonArray = response.getJSONArray(JsonSearch.JsonArrayName);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        // получаем по индексу jsonobject
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        // извлекаем данные по ключу
                        String title = jsonObject.getString(JsonSearch.title);
                        String year = jsonObject.getString(JsonSearch.year);
                        // получаем ссылку на изображение и проверяем ее валидность
                        String urlPath = preparingImage(jsonObject.getString(JsonSearch.poster));
                        String type = jsonObject.getString(JsonSearch.type);
                        // ищем id для того чтобы потом найти доп.информацию
                        String imdbID = jsonObject.getString(JsonSearch.imdbID);

                        Movies moviesObject = new Movies();

                        moviesObject.setTitle(title);
                        moviesObject.setYear(year);
                        moviesObject.setPosterUrlPath(urlPath);
                        moviesObject.setType(type);
                        moviesObject.setImbID(imdbID);

                        movies.add(moviesObject);

                    }

                    movieAdapter = new MovieAdapter(activity,
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
                Toast.makeText(activity,
                        "Произошла ошибка попробуйте еще", Toast.LENGTH_LONG);
                error.printStackTrace();


            }
        });
        requestQueue.add(request);// добавляем в очередь запросов
    }


    private String preparingImage(String imageUrl) {
        String result;
        if (imageUrl.equals(JsonSearch.notFindImageMovie)) {
            // устанавливаем изображение по умолчанию
            result = JsonSearch.notFindImageMovie;
            return result;
        } else {
            result = imageUrl;
            return result;
        }
    }

    public void moreInfoMovie() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,// GET запрос
                urlInfoMovie, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                /*
                парсинг JSON
                 */
                try {
                    String[] array = new String[]{response.getString(infoMovie.title),
                            response.getString(infoMovie.year),response.getString(infoMovie.rated),
                            response.getString(infoMovie.runtime),
                            response.getString(infoMovie.released),
                            response.getString(infoMovie.genre),
                            response.getString(infoMovie.actors),
                            response.getString(infoMovie.plot),
                            response.getString(infoMovie.imdbRating),
                            response.getString(JsonSearch.poster)};
                    listJsonRes.addAll(Arrays.asList(array));

                    ArrayList<ArrayList<String>> resultList = new ArrayList<>();
                    resultList.add(listJsonRes);
                    InfoAdapter infoAdapter = new InfoAdapter(infoActivity,
                            resultList);
                    recyclerView.setAdapter(infoAdapter);

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
                Toast.makeText(infoActivity,
                        "Произошла ошибка попробуйте еще", Toast.LENGTH_LONG);
                error.printStackTrace();


            }
        });
        requestQueue.add(request);// добавляем в очередь запросов
    }


    public static ArrayList<String> getListJsonRes(){
        return listJsonRes;
    }

    private void downloadAndSetImage(String poster){
        Picasso.get().load(poster).fit().centerInside()
                .into((ImageView) infoActivity.findViewById(R.id.mainImage));
    }


    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getUrlUsers() {
        return urlUsers;
    }

    public void setUrlUsers(String urlUsers) {
        this.urlUsers = urlUsers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
