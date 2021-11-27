package com.example.project;

import android.app.Application;
import android.database.Cursor;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;


import java.util.List;

public class LessonViewModel extends AndroidViewModel {

    private LessonRepository lessonRepository;
    private Lesson lesson;
    private Cursor categoryLessons;


    public LessonViewModel(@NonNull Application application) {
        super(application);
        lessonRepository = new LessonRepository(application);


    }

    public void insert(Lesson lesson) {

        lessonRepository.Insert(lesson);
    }

    public void update(Lesson lesson) {
       lessonRepository.Update(lesson);
    }

    public void delete(Lesson lesson) {
        lessonRepository.Delete(lesson);
    }

    public Lesson getLesson(int lessonChoice) {

        return lesson = lessonRepository.getLesson(lessonChoice);

    }

    public Cursor getCategoryLessons(int categoryChoice) {
        categoryLessons = lessonRepository.getCategoryLessons(categoryChoice);
        return  categoryLessons;
    }
}
