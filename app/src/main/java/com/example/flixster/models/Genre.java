package com.example.flixster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Genre {
    int id;
    String name;

    public Genre() {}

    public Genre(JSONObject jsonObject) throws JSONException {
        id = jsonObject.getInt("id");
        name = jsonObject.getString("name");
    }

    public static List<Genre> fromJsonArray(JSONArray movieJasonArray) throws JSONException {
        List<Genre> genres = new ArrayList<>();
        for (int i = 0; i < movieJasonArray.length(); i++) {
            genres.add(new Genre(movieJasonArray.getJSONObject(i)));
        }
        return genres;
    }

    public int getid() {return id;}

    public String getName() {return name;}

}


