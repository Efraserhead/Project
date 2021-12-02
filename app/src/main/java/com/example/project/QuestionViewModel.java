package com.example.project;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.List;

public class QuestionViewModel extends AndroidViewModel {


    private Repository repository;
    private List<Question> questions;


    public QuestionViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }


    public void insert(Question question) {
        repository.insertQuestion(question);
    }



    public void delete(Question question) {
        repository.deleteQuestion(question);
    }


    public void update(Question question) {
        repository.updateQuestion(question);
    }



    public List<Question> getLessonQuestions(int lessonNumber) {
        return questions = repository.getLessonQuestions(lessonNumber);
    }
}
