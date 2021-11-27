package com.example.project;

import android.app.Application;

import java.util.List;

public class ProblemRepository {

    private ProblemDao problemDao;

    private List<Problem> lessonProblems, unlockedProblems, allProblems;

    public ProblemRepository(Application application) {
        ProjectDatabase projectDatabase = ProjectDatabase.getInstance(application);
        problemDao = projectDatabase.problemDao();
    }

    public void insert(Problem problem) {
        problemDao.insert(problem);
    }

    public void delete(Problem problem) {
        problemDao.delete(problem);
    }

    public void update(Problem problem) {
        problemDao.update(problem);
    }

    public List<Problem> getLessonProblems(int lessonNo) {
       return lessonProblems = problemDao.getLessonProblems(lessonNo);
    }


    public List<Problem> getUnlockedProblems() {
       return unlockedProblems = problemDao.getUnlockedProblems();
    }

    public List<Problem> getAllProblems() {
        return allProblems = problemDao.getProblems();
    }
}
