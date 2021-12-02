package com.example.project;

import android.app.Application;
import android.database.Cursor;

import java.util.List;

public class Repository {
    private CategoryDao categoryDao;
    private QuestionDao questionDao;
    private LessonDao lessonDao;
    private ProblemDao problemDao;

    public Repository(Application application) {
        ProjectDatabase projectDatabase = ProjectDatabase.getInstance(application);
        categoryDao = projectDatabase.categoryDao();
        questionDao = projectDatabase.questionDao();
        lessonDao = projectDatabase.lessonDao();
        problemDao = projectDatabase.problemDao();
    }

    public void insertCategory(Category category) {
        categoryDao.insert(category);
    }


    public void deleteCategory(Category category) {
        categoryDao.delete(category);
    }


    public void updateCategory(Category category){
        categoryDao.update(category);
    }


    public Category getCategory(int categoryChoice) {
        return categoryDao.getCategory(categoryChoice);
    }


    public List<Category> getAllCategories() {
        return categoryDao.getAll();
    }


    public void insertLesson(Lesson lesson) {

        lessonDao.insert(lesson);

    }

    public void updateLesson(Lesson lesson) {

        lessonDao.update(lesson);
    }

    public void deleteLesson(Lesson lesson) {


        lessonDao.delete(lesson);
    }

    public Lesson getLesson(int lessonChoice) {

        return lessonDao.getLesson(lessonChoice);
    }

    public Cursor getCategoryLessons(int categoryChoice) {

        return lessonDao.getCategoryLessons(categoryChoice);
    }


    public void insertQuestion(Question question) {
        questionDao.insert(question);
    }



    public void deleteQuestion(Question question) {
        questionDao.delete(question);
    }


    public void updateQuestion(Question question) {
        questionDao.update(question);
    }



    public List<Question> getLessonQuestions(int lessonNumber) {
        return questionDao.getLessonQuestions(lessonNumber);
    }

    public void insertProblem(Problem problem) {
        problemDao.insert(problem);
    }

    public void deleteProblem(Problem problem) {
        problemDao.delete(problem);
    }

    public void updateProblem(Problem problem) {
        problemDao.update(problem);
    }

    public List<Problem> getLessonProblems(int lessonNo) {
        return problemDao.getLessonProblems(lessonNo);
    }


    public List<Problem> getUnlockedProblems() {
        return problemDao.getUnlockedProblems();
    }

    public List<Problem> getAllProblems() {
        return problemDao.getProblems();
    }
}
