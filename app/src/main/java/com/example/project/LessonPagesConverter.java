package com.example.project;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class LessonPagesConverter {

    @TypeConverter
    public static String fromLessonPages(ArrayList<LessonPage> lessonPages) {
        Gson gson = new Gson();
        String json = gson.toJson(lessonPages);
        return json;
    }

    @TypeConverter
    public static ArrayList<LessonPage> toLessonPages(String lessonPages) {
        Type listType = new TypeToken<ArrayList<LessonPage>>() {}.getType();
        return new Gson().fromJson(lessonPages, listType);
    }
}
