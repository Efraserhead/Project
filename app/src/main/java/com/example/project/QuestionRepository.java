package com.example.project;

import android.app.Application;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

public class QuestionRepository {
    private QuestionDao questionDao;
    private List<Question> questions;

    public QuestionRepository(Application application) {
        ProjectDatabase projectDatabase = ProjectDatabase.getInstance(application);
        questionDao = projectDatabase.questionDao();
    }



   public void insert(Question question) {
        questionDao.insert(question);
   }



    public void delete(Question question) {
        questionDao.delete(question);
    }


    public void update(Question question) {
        questionDao.update(question);
    }



    public List<Question> getLessonQuestions(int lessonNumber) {
        return questions = questionDao.getLessonQuestions(lessonNumber);
    }

}
