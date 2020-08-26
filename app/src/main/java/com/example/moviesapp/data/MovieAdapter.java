package com.example.moviesapp.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviesapp.R;
import com.example.moviesapp.model.Movies;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private Context context;
    private ArrayList<Movies> movies;

    public MovieAdapter(Context context, ArrayList<Movies> movies) {
        this.context = context;
        this.movies = movies;
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView mainPosterImageView;
        TextView titleTextView;
        TextView yearTextView;
        TextView typeTextView;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            searchFields();
        }

        private void searchFields() {
            mainPosterImageView = itemView.findViewById(R.id.mainPosterImageView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            yearTextView = itemView.findViewById(R.id.yearTextView);
            typeTextView = itemView.findViewById(R.id.typeTextView);
        }
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
        Movies currentMovieElement = movies.get(position);

        String title = currentMovieElement.getTitle();
        String year = currentMovieElement.getYear();
        String posterUrlPath = currentMovieElement.getPosterUrlPath();
        String type = currentMovieElement.getType();

        holder.titleTextView.setText(title);
        holder.yearTextView.setText(year);
        holder.typeTextView.setText(type);
        /*
        Загружаем изображение с помощью библиотеки Picasso
        и устанавиваем его в ImageView
         */
        Picasso.get().load(posterUrlPath).fit().centerInside().into(holder.mainPosterImageView);


    }



    @Override
    public int getItemCount() {
        return movies.size();
    }


}
