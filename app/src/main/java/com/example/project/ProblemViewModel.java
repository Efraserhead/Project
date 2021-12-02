package com.example.project;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.List;

public class ProblemViewModel extends AndroidViewModel {

    private Repository repository;

    private List<Problem> lessonProblems, unlockedProblems, allProblems;


    public ProblemViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public void insert(Problem problem) {
        repository.insertProblem(problem);
    }

    public void delete(Problem problem) {
        repository.deleteProblem(problem);
    }

    public void update(Problem problem) {
        repository.updateProblem(problem);
    }

    public List<Problem> getLessonProblems(int lessonNo) {
        return lessonProblems = repository.getLessonProblems(lessonNo);
    }


    public List<Problem> getUnlockedProblems() {
        return unlockedProblems = repository.getUnlockedProblems();
    }

    public List<Problem> getAllProblems() {
        return allProblems = repository.getAllProblems();
    }
}
