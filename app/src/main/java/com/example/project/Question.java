package com.example.project;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "question_table")
public class Question {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String question;
    private String answerId0;
    private String answerId1;
    private String answerId2;
    private int correctAnswerId;
    private int lessonNumber;


    public Question(String question, String answerId0, String answerId1, String answerId2, int correctAnswerId, int lessonNumber) {
        this.question = question;
        this.answerId0 = answerId0;
        this.answerId1 = answerId1;
        this.answerId2 = answerId2;
        this.correctAnswerId = correctAnswerId;
        this.lessonNumber = lessonNumber;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswerId0() {
        return answerId0;
    }

    public void setAnswerId0(String answerId0) {
        this.answerId0 = answerId0;
    }

    public String getAnswerId1() {
        return answerId1;
    }

    public void setAnswerId1(String answerId1) {
        this.answerId1 = answerId1;
    }

    public String getAnswerId2() {
        return answerId2;
    }

    public void setAnswerId2(String answerId2) {
        this.answerId2 = answerId2;
    }

    public int getCorrectAnswerId() {
        return correctAnswerId;
    }

    public void setCorrectAnswerId(int correctAnswerId) {
        this.correctAnswerId = correctAnswerId;
    }

    public int getLessonNumber() {
        return lessonNumber;
    }

    public void setLessonNumber(int lessonNumber) {
        this.lessonNumber = lessonNumber;
    }
}