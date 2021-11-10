package com.example.project;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;


public class LessonPagesConverter {


    public static String fromLessonPages(LessonPage[] lessonPages) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<LessonPage>>() {
        }.getType();
        String json = gson.toJson(lessonPages, type);
        return json;
    }

    public static List<LessonPage> toLessonPages(String lessonPages) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<LessonPage>>(){
        }.getType();
        List<LessonPage> lessonPageList = gson.fromJson(lessonPages, type);
        return lessonPageList;
    }
}
