package com.example.moviesapp.data;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviesapp.R;
import com.example.moviesapp.activites.MovieInfoActivity;
import com.example.moviesapp.model.Movies;
import com.squareup.picasso.Picasso;
import com.example.moviesapp.utils.JsonFields.Urls;

import java.util.ArrayList;
import java.util.LinkedHashSet;

import static com.example.moviesapp.utils.JsonFields.JsonSearch.*;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private Context context;
    private ArrayList<Movies> movies;
    public static LinkedHashSet<String> imbMainIdArray = new LinkedHashSet<String>();

    public MovieAdapter(Context context, ArrayList<Movies> movies) {
        this.context = context;
        this.movies = movies;
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {// реализуем интерфейс чтобы реализовать метод OnClick
        // содержимое CardView
        ImageView mainPosterImageView;
        TextView titleTextView;
        TextView yearTextView;
        TextView typeTextView;
        TextView imbIdTextView;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);// установить обработчик на CardView
            searchFields();
        }


        private void searchFields() {
            mainPosterImageView = itemView.findViewById(R.id.posterImageView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            yearTextView = itemView.findViewById(R.id.yearTextView);
            typeTextView = itemView.findViewById(R.id.typeTextView);
            imbIdTextView = itemView.findViewById(R.id.imbIdTextView);
        }


        @Override
        public void onClick(View view) {
            // метод срабатывает если мы нажимаем на CardView
            int counter = -1;
            int position = getAdapterPosition();// получаем позицию CardView
            Intent intent = new Intent(context, MovieInfoActivity.class);
            LinkedHashSet<String> setId = getImbMainId();
            for (String element : setId) {
                counter++;
                if (counter == position) {
                    pullExtraInfoOtherActivity(intent, element);
                }
            }


        }
    }

    private void pullExtraInfoOtherActivity(Intent intent, String element) {
        intent.putExtra("json_url", Urls.infoMovieUrl);
        intent.putExtra("json_id", element);
        intent.putExtra("json_api_key", Urls.API_KEY);
        context.startActivity(intent);
    }

    private void loadImagePicasso(String posterUrlPath, MovieViewHolder holder) {
        /*
        Загружаем изображение с помощью библиотеки Picasso
        и устанавиваем его в ImageView
         */
        Picasso.get().load(posterUrlPath).fit().centerInside()
                .into(holder.mainPosterImageView);
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /*
        если требуется создается новый элемент
         */
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);

        MovieViewHolder movieViewHolder = new MovieViewHolder(view);
        return movieViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        /*
        Метод отвечает за заполнение CardView
         */

        Movies currentMovieElement = movies.get(position);

        String title = currentMovieElement.getTitle();
        String year = currentMovieElement.getYear();
        String posterUrlPath = currentMovieElement.getPosterUrlPath();
        String type = currentMovieElement.getType();
        String imbId = currentMovieElement.getImbID();
        /*
        добавляем id в LinkedHashSet для того чтобы потом
        при нажатии на CardView взять id по position
        и испольщовать для дальнейшего парсинга Json
         */
        imbMainIdArray.add(imbId);

        holder.titleTextView.setText(title);
        holder.yearTextView.setText(year);
        holder.typeTextView.setText(type);
        holder.imbIdTextView.setText(imbId);
        Log.d("image", posterUrlPath);
        if (posterUrlPath.equals(notFindImageMovie)) {
            // если изображение отсутсвует устнавливаем изображение по умолчанию
            holder.mainPosterImageView.setImageResource(defaultPathImage);

        } else {
            // загружаем изображение
            loadImagePicasso(posterUrlPath, holder);
        }

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static LinkedHashSet<String> getImbMainId() {
        return imbMainIdArray;
    }

    public static void cleanImbMainId() {
        imbMainIdArray.clear();
    }
}
