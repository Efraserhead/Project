package com.example.project;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.List;

public class QuestionViewModel extends AndroidViewModel {

    private QuestionRepository questionRepository;
    private List<Question> questions;


    public QuestionViewModel(@NonNull Application application) {
        super(application);
        questionRepository = new QuestionRepository(application);
    }


    public void insert(Question question) {
        questionRepository.insert(question);
    }



    public void delete(Question question) {
        questionRepository.delete(question);
    }


    public void update(Question question) {
        questionRepository.update(question);
    }



    public List<Question> getLessonQuestions(int lessonNumber) {
        return questions = questionRepository.getLessonQuestions(lessonNumber);
    }
}
