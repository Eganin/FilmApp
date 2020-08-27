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

import static com.example.moviesapp.utils.JsonFields.JsonSearch.defaultPathImage;
import static com.example.moviesapp.utils.JsonFields.JsonSearch.notFindImageMovie;

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.InfoViewHolder> {

    private Context context;
    private ArrayList<ArrayList<String>> infoMovie;

    public InfoAdapter(Context context, ArrayList<ArrayList<String>> infoMovie) {
        this.context = context;
        this.infoMovie = infoMovie;
    }

    @NonNull
    @Override
    public InfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /*
        если требуется создается новый элемент
         */
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_info, parent, false);

        InfoAdapter.InfoViewHolder infoViewHolder = new InfoAdapter.InfoViewHolder(view);
        return infoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull InfoViewHolder holder, int position) {
        ArrayList<String> arrayList = infoMovie.get(position);

        holder.titleTextView.append(arrayList.get(0));
        holder.yearTextView.append(arrayList.get(1));
        holder.ratedTextView.append(arrayList.get(2));
        holder.runtimeTextView.append(arrayList.get(3));
        holder.releasedTextView.append(arrayList.get(4));
        holder.genreTextView.append(arrayList.get(5));
        holder.actorsTextView.append(arrayList.get(6));
        holder.plotTextView.append(arrayList.get(7));
        holder.rating1TextView.append(arrayList.get(8));

        loadImagePicasso(arrayList.get(9), holder);

        arrayList.clear();
    }

    @Override
    public int getItemCount() {
        return infoMovie.size();
    }

    private void loadImagePicasso(String posterUrlPath, InfoAdapter.InfoViewHolder holder) {
        /*
        Загружаем изображение с помощью библиотеки Picasso
        и устанавиваем его в ImageView
         */
        if(posterUrlPath.equals(notFindImageMovie)){
            holder.mainImage.setImageResource(defaultPathImage);
        }else{
            Picasso.get().load(posterUrlPath).fit().centerInside()
                    .into(holder.mainImage);
        }

    }

    public class InfoViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        // содержимое CardView
        ImageView mainImage;
        TextView titleTextView;
        TextView yearTextView;
        TextView ratedTextView;
        TextView runtimeTextView;
        TextView releasedTextView;
        TextView genreTextView;
        TextView actorsTextView;
        TextView plotTextView;
        TextView rating1TextView;

        public InfoViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);// установить обработчик на CardView
            searchFields();
        }


        private void searchFields() {
            mainImage = itemView.findViewById(R.id.mainImage);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            yearTextView = itemView.findViewById(R.id.yearTextView);
            ratedTextView = itemView.findViewById(R.id.ratedTextView);
            runtimeTextView = itemView.findViewById(R.id.runtimeTextView);
            releasedTextView = itemView.findViewById(R.id.releasedTextView);
            genreTextView = itemView.findViewById(R.id.genreTextView);
            actorsTextView = itemView.findViewById(R.id.actorsTextView);
            plotTextView = itemView.findViewById(R.id.plotTextView);
            rating1TextView = itemView.findViewById(R.id.rating1TextView);
        }


        @Override
        public void onClick(View view) {

        }
    }
}
