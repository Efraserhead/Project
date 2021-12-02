package com.example.project;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ResourcesListConverter {

    @TypeConverter
    public static String fromResources(ArrayList<String> resources) {
        Gson gson = new Gson();
        String json = gson.toJson(resources);
        return json;
    }

    @TypeConverter
    public static ArrayList<String> toResources(String resources) {
        Type listType = new TypeToken<ArrayList<String>>() {}.getType();
        return new Gson().fromJson(resources, listType);
    }
}
