package com.example.project;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@TypeConverters({LessonPagesConverter.class, DateConverter.class, ResourcesListConverter.class})
@Database(entities = {Lesson.class, Problem.class, Question.class, Category.class}, version = 1)
public abstract class ProjectDatabase extends RoomDatabase {

    private static volatile ProjectDatabase instance;


    public abstract LessonDao lessonDao();

    public abstract ProblemDao problemDao();

    public abstract QuestionDao questionDao();

    public abstract CategoryDao categoryDao();

    public static synchronized ProjectDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ProjectDatabase.class, "project_database")
                    .fallbackToDestructiveMigration()
                    .build();


        }
        return instance;
    }


}
