package com.example.project;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Update;

import java.util.List;

public class LessonRepository {

    private LessonDao lessonDao;

    Lesson lesson;
    List<Lesson> categoryLessons;



    public LessonRepository(Application application, int lessonChoice, int categoryChoice) {
        ProjectDatabase projectDatabase = ProjectDatabase.getInstance(application);
        lessonDao = projectDatabase.lessonDao();
        categoryLessons = lessonDao.getCategoryLessons(categoryChoice);
        lesson = lessonDao.getLesson(lessonChoice);


    }

    public void Insert(Lesson lesson) {
        new InsertLessonAsyncTask(lessonDao).execute(lesson);

    }

    public void Update(Lesson lesson) {
        new UpdateLessonAsyncTask(lessonDao).execute(lesson);
    }

    public void Delete(Lesson lesson) {
        new DeleteLessonAsyncTask(lessonDao).execute(lesson);
    }

    public Lesson getLesson(int lessonChoice) {
        return lesson;
    }

    public List<Lesson> getCategoryLessons(int categoryChoice) {

        return categoryLessons;
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
