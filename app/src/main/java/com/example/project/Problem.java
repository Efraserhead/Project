package com.example.project;


import android.graphics.drawable.Drawable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity(tableName = "problem_table")
public class Problem {


    private int problemCode;

    private String answer, problemQuestion;



    @TypeConverters(DateConverter.class)
    private Date nextAvailable;


    private int lessonId, level;

    @PrimaryKey(autoGenerate = true)
    private int id;

    public Problem(int problemCode, String answer, String problemQuestion, Date nextAvailable, int lessonId, int level) {
        this.problemCode = problemCode;
        this.answer = answer;
        this.problemQuestion = problemQuestion;
        this.nextAvailable = nextAvailable;
        this.lessonId = lessonId;
        this.level = level;
    }

    public int getProblemCode() {
        return problemCode;
    }

    public void setProblemCode(int problemCode) {
        this.problemCode = problemCode;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getProblemQuestion() {
        return problemQuestion;
    }

    public void setProblemQuestion(String problemQuestion) {
        this.problemQuestion = problemQuestion;
    }

    public Date getNextAvailable() {
        return nextAvailable;
    }

    public void setNextAvailable(Date nextAvailable) {
        this.nextAvailable = nextAvailable;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}