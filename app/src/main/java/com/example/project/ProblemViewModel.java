package com.example.project;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.List;

public class ProblemViewModel extends AndroidViewModel {

    private ProblemRepository problemRepository;

    private List<Problem> lessonProblems, unlockedProblems, allProblems;


    public ProblemViewModel(@NonNull Application application) {
        super(application);
        problemRepository = new ProblemRepository(application);
    }

    public void insert(Problem problem) {
        problemRepository.insert(problem);
    }

    public void delete(Problem problem) {
        problemRepository.delete(problem);
    }

    public void update(Problem problem) {
        problemRepository.update(problem);
    }

    public List<Problem> getLessonProblems(int lessonNo) {
        return lessonProblems = problemRepository.getLessonProblems(lessonNo);
    }


    public List<Problem> getUnlockedProblems() {
        return unlockedProblems = problemRepository.getUnlockedProblems();
    }

    public List<Problem> getAllProblems() {
        return allProblems = problemRepository.getAllProblems();
    }
}
