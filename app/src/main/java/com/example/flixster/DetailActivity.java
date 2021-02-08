package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.flixster.models.Genre;
import com.example.flixster.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    TextView tvTitle;
    TextView tvOverview;
    RatingBar ratingBar;
    TextView ageRating;
    TextView genreList;
    String genres = "";
    TextView releaseDate;
    TextView voteCount;
    TextView popularity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvTitle = findViewById(R.id.tvTitle);
        tvOverview = findViewById(R.id.tvOverview);
        ratingBar = findViewById(R.id.ratingBar);
        ageRating = findViewById(R.id.ageRating);
        genreList = findViewById(R.id.genreList);
        releaseDate = findViewById(R.id.releaseDate);
        voteCount = findViewById(R.id.voteCount);
        popularity = findViewById(R.id.popularity);

        Movie movie = Parcels.unwrap(getIntent().getParcelableExtra("movie"));
        List<Integer> genreids = getIntent().getIntegerArrayListExtra("genreids");
        List<String> genreNames = getIntent().getStringArrayListExtra("genreNames");


        for (int i = 0; i < movie.getGenreList().size(); i++) {
            for (int k = 0; k < genreids.size(); k++) {
                    if (genreids.get(k) == movie.getGenreList().get(i)) {
                        if (genres == "") {
                            genres += genreNames.get(k);
                        }
                        else {
                            genres += ", " + genreNames.get(k);
                        }
                    }
            }
        }

        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());
        ratingBar.setRating((float) movie.getRating());
        genreList.setText("Genre: " + genres);
        releaseDate.setText("Release Date: " + movie.getReleaseDate());
        voteCount.setText("# of votes: " + movie.getVoteCount());
        popularity.setText("Popularity: " + movie.getPopularity());
        if (movie.getAgeRating())
        {
            ageRating.setText("Adult? : Yes");
        }
        else
        {
            ageRating.setText("Adult? : No");
        }
    }
}