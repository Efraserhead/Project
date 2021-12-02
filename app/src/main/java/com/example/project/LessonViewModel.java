package com.example.project;

import android.app.Application;
import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class LessonViewModel extends AndroidViewModel {
    private Repository repository;
    private Lesson lesson;
    private Cursor categoryLessons;


    public LessonViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);


    }

    public void insert(Lesson lesson) {

        repository.insertLesson(lesson);
    }

    public void update(Lesson lesson) {
       repository.updateLesson(lesson);
    }

    public void delete(Lesson lesson) {
        repository.deleteLesson(lesson);
    }

    public Lesson getLesson(int lessonChoice) {

        lesson = repository.getLesson(lessonChoice);
        return lesson;

    }

    public Cursor getCategoryLessons(int categoryChoice) {
        categoryLessons = repository.getCategoryLessons(categoryChoice);
        return  categoryLessons;
    }
}
