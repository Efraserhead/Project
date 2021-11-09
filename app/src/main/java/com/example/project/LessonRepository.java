package com.example.project;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Update;

import java.util.List;

public class LessonRepository {

    private LessonDao lessonDao;

    LiveData<Lesson> lesson;
    LiveData<List<Lesson>> categoryLessons;

    public static final LessonPage[] variables = {
            new LessonPage(R.string.lesson1_1,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_2, R.drawable.brain_2),
            new LessonPage(R.string.lesson1_3,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_4,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_5,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_6,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_7,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_8,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_9,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_10,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_11,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_12,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_13,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_14,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_15,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_16,R.drawable.brain_2)};

    public LessonRepository(Application application) {
        ProjectDatabase projectDatabase = ProjectDatabase.getInstance(application);
        lessonDao = projectDatabase.lessonDao();


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

    public LiveData<Lesson> getLesson(int lessonChoice) {
        lesson = lessonDao.getLesson(lessonChoice);
        return lesson;
    }

    public LiveData<List<Lesson>> getCategoryLessons(int categoryChoice) {
        categoryLessons = lessonDao.getCategoryLessons(categoryChoice);
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
