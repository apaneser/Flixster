package com.example.flixster.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixster.DetailActivity;
import com.example.flixster.R;
import com.example.flixster.models.Genre;
import com.example.flixster.models.Movie;

import org.apache.commons.cli.Parser;
import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{

    Context context;
    List<Movie> movies;
    List<Genre> genres;

    public MovieAdapter(Context context, List<Movie> movies, List<Genre> genres) {
        this.context = context;
        this.movies = movies;
        this.genres = genres;
    }


    //involves inflating a layout from XML and returning the holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("MovieAdapter", "onCreateViewHolder");
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(movieView);
    }

    //involves populating data into the item through holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("MovieAdapter", "onCreateBindHolder");
        //get the movie at the passed in position
        Movie movie = movies.get(position);
        List<Integer> genreids = new ArrayList<>();
        for(int i = 0; i < genres.size(); i++)
        {
            genreids.add(genres.get(i).getid());
        }
        List<String> genreNames = new ArrayList<>();
        for(int j = 0; j < genres.size(); j++)
        {
            genreNames.add(genres.get(j).getName());
        }

        //bind movie data into VM
        holder.bind(movie, genreids, genreNames);
    }

    //return total count of items in the list
    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout container;
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);
            container = itemView.findViewById((R.id.RelativeLayout));
        }

        public void bind(Movie movie, List<Integer> genreids, List<String> genreNames) {
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            String imageURL;
            //if phone is in landscape
            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                //set imageurl = backdrop
                imageURL = movie.getBackdropPath();
                }
            else {
                //else imageURL = posterimage
                imageURL = movie.getPosterPath();
            }
            Glide.with(context).load(imageURL).into(ivPoster);
            //register clickListener on whole row
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //navigate to a new activity on tap
                    Intent i = new Intent(context, DetailActivity.class);
                    i.putExtra("movie", Parcels.wrap(movie));
                    i.putIntegerArrayListExtra("genreids", (ArrayList<Integer>) genreids);
                    i.putStringArrayListExtra("genreNames", (ArrayList<String>) genreNames);
                    context.startActivity(i);
                }
            });
        }
    }
}
