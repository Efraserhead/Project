package com.example.project;

import android.app.Application;
import android.database.Cursor;
import android.os.AsyncTask;


import java.util.List;

public class LessonRepository {

    private LessonDao lessonDao;

    Lesson lesson;
    Cursor categoryLessons;



    public LessonRepository(Application application) {
        ProjectDatabase projectDatabase = ProjectDatabase.getInstance(application);
        lessonDao = projectDatabase.lessonDao();



    }

    public void Insert(Lesson lesson) {

        lessonDao.insert(lesson);

    }

    public void Update(Lesson lesson) {

        lessonDao.update(lesson);
    }

    public void Delete(Lesson lesson) {


        lessonDao.delete(lesson);
    }

    public Lesson getLesson(int lessonChoice) {

        return lesson = lessonDao.getLesson(lessonChoice);
    }

    public Cursor getCategoryLessons(int categoryChoice) {

        return categoryLessons = lessonDao.getCategoryLessons(categoryChoice);
    }

    private static class InsertLessonAsyncTask extends AsyncTask<Lesson,Void,Void> {
        private LessonDao lessonDao;

        private InsertLessonAsyncTask(LessonDao lessonDao) {
            this.lessonDao = lessonDao;
        }

        @Override
        protected Void doInBackground(Lesson... lessons) {
            lessonDao.insert(lessons[0]);
            return null;
        }
    }

    private static class UpdateLessonAsyncTask extends AsyncTask<Lesson,Void,Void> {
        private LessonDao lessonDao;

        private UpdateLessonAsyncTask(LessonDao lessonDao) {
            this.lessonDao = lessonDao;
        }

        @Override
        protected Void doInBackground(Lesson... lessons) {
            lessonDao.update(lessons[0]);
            return null;
        }
    }

    private static class DeleteLessonAsyncTask extends AsyncTask<Lesson,Void,Void> {
        private LessonDao lessonDao;

        private DeleteLessonAsyncTask(LessonDao lessonDao) {
            this.lessonDao = lessonDao;
        }

        @Override
        protected Void doInBackground(Lesson... lessons) {
            lessonDao.delete(lessons[0]);
            return null;
        }
    }



}
