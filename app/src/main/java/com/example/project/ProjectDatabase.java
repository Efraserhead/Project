package com.example.project;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Lesson.class,Problem.class,Question.class},version=1)
public abstract class ProjectDatabase extends RoomDatabase {

    private static ProjectDatabase instance;


    public abstract LessonDao lessonDao();

    public abstract ProblemDao problemDao();

    public abstract QuestionDao questionDao();

    public static synchronized ProjectDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ProjectDatabase.class, "project_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new AddAllLessonsAsyncTask(instance).execute();
            new AddAllQuestionsAsyncTask(instance).execute();
            new AddAllProblemsAsyncTask(instance).execute();
        }
    };

    private static class AddAllLessonsAsyncTask extends AsyncTask<Void, Void, Void> {
        private LessonDao lessonDao;

        private AddAllLessonsAsyncTask(ProjectDatabase projectDatabase) {
            lessonDao = projectDatabase.lessonDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            lessonDao.insert(new Lesson("getting started", 1, 0,Lesson.introduction));
            lessonDao.insert(new Lesson("variables", 2, 0,Lesson.variables));
            lessonDao.insert(new Lesson("data types", 2, 0,Lesson.dataTypes));
            lessonDao.insert(new Lesson("operators", 2, 0,Lesson.operators));
            lessonDao.insert(new Lesson("strings", 2, 0,Lesson.stringsPages));
            lessonDao.insert(new Lesson("control structures", 3, 0,Lesson.controlStructures));
            lessonDao.insert(new Lesson("looping 1", 3, 0,Lesson.looping1));
            lessonDao.insert(new Lesson("looping 2", 3, 0,Lesson.looping2));
            lessonDao.insert(new Lesson("syntax", 3, 0,Lesson.syntax));
            lessonDao.insert(new Lesson("data structures", 4, 0,Lesson.dataStructures));
            lessonDao.insert(new Lesson("functions 1", 4, 0,Lesson.functions1));
            lessonDao.insert(new Lesson("functions 2", 4, 0,Lesson.functions2));
            lessonDao.insert(new Lesson("debugging", 4, 0,Lesson.debugging));

            return null;
        }
    }

    private static class AddAllQuestionsAsyncTask extends AsyncTask<Void, Void, Void> {
        private QuestionDao questionDao;

        private AddAllQuestionsAsyncTask(ProjectDatabase projectDatabase) {
            questionDao = projectDatabase.questionDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }

    private static class AddAllProblemsAsyncTask extends AsyncTask<Void, Void, Void> {
        private ProblemDao problemDao;

        private AddAllProblemsAsyncTask(ProjectDatabase projectDatabase) {
            problemDao = projectDatabase.problemDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }


}