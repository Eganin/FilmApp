package com.example.moviesapp.utils;

import android.widget.ImageView;
import android.widget.TextView;
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
import com.example.moviesapp.data.MovieAdapter;
import com.example.moviesapp.model.Movies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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

    public JsonMainAdapter() {
    }

    public JsonMainAdapter(String urlUsers, ArrayList<Movies> movies, RecyclerViewActivity activty,
                           RecyclerView recyclerView, String mode) {
        this.urlUsers = urlUsers;
        this.mode = mode;
        this.movies = movies;
        this.activity = activty;
        requestQueue = Volley.newRequestQueue(activty);
        this.recyclerView = recyclerView;
    }

    public JsonMainAdapter(String urlInfoMovie, String id, String apiKey, MovieInfoActivity activity) {
        this.id = id;
        this.urlInfoMovie = String.format(urlInfoMovie, id, apiKey);
        requestQueue = Volley.newRequestQueue(activity);

    }


    public void searchMovies() {
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

                    String title = response.getString(infoMovie.title);
                    String  year = response.getString(infoMovie.year);
                    String rated = response.getString(infoMovie.rated);
                    String runtime = response.getString(infoMovie.runtime);
                    String released = response.getString(infoMovie.released);
                    String genre = response.getString(infoMovie.genre);
                    String actors = response.getString(infoMovie.actors);
                    String plot = response.getString(infoMovie.plot);
                    String imdbRating = response.getString(infoMovie.imdbRating);
                    String poster = response.getString(JsonSearch.poster);

                    TextView titleTextView  = infoActivity.findViewById(R.id.titleTextView);
                    TextView yearTextView  = infoActivity.findViewById(R.id.yearTextView);
                    TextView ratedTextView  = infoActivity.findViewById(R.id.ratedTextView);
                    TextView runtimeTextView  = infoActivity.findViewById(R.id.runtimeTextView);
                    TextView releasedTextView  = infoActivity.findViewById(R.id.releasedTextView);
                    TextView genreTextView  = infoActivity.findViewById(R.id.genreTextView);
                    TextView actorsTextView  = infoActivity.findViewById(R.id.actorsTextView);
                    TextView plotTextView  = infoActivity.findViewById(R.id.plotTextView);
                    TextView rating1TextView  = infoActivity.findViewById(R.id.rating1TextView);


                    titleTextView.append(title);
                    yearTextView.append(year);
                    ratedTextView.append(rated);
                    runtimeTextView.append(runtime);
                    releasedTextView.append(released);
                    genreTextView.append(genre);
                    actorsTextView.append(actors);
                    plotTextView.append(plot);
                    rating1TextView.append(imdbRating);

                    downloadAndSetImage(poster);

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
}
