package com.example.flixster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Movie {

    String posterPath;
    String backdropPath;
    String title;
    String overview;
    double rating;
    boolean ageRating;
    List<Integer> genreList = new ArrayList<>();
    String releaseDate;
    String voteCount;
    String popularity;

    //empty construct needed by parceler
    public Movie() {}

    public Movie(JSONObject jsonObject) throws JSONException {
        backdropPath = jsonObject.getString("backdrop_path");
        posterPath = jsonObject.getString("poster_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
        rating = jsonObject.getDouble("vote_average");
        ageRating = jsonObject.getBoolean("adult");
        releaseDate = jsonObject.getString("release_date");
        voteCount = jsonObject.getString("vote_count");
        popularity = jsonObject.getString("popularity");
        JSONArray list = jsonObject.getJSONArray("genre_ids");

        if (list != null) {
            for (int i=0;i<list.length();i++){
                genreList.add(list.getInt(i));
            }
        }

    }

    public static List<Movie> fromJsonArray(JSONArray movieJasonArray) throws JSONException {
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < movieJasonArray.length(); i++) {
            movies.add(new Movie(movieJasonArray.getJSONObject(i)));
        }
        return movies;
    }

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public String getBackdropPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s",backdropPath);
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public double getRating() { return rating; }

    public boolean getAgeRating() {return ageRating; }

    public String getReleaseDate() {return releaseDate;}

    public String getVoteCount() {return voteCount;}

    public String getPopularity() { return popularity; }

    public List<Integer> getGenreList() {return genreList;}
}
