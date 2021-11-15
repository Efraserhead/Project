package com.example.project;


import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity(tableName = "problem_table")
public class Problem {


    private String problem, answer, problemCode;

    @TypeConverters(DateConverter.class)
    private Date nextAvailable;


    private int lessonNo, level;

    @PrimaryKey(autoGenerate = true)
    private int id;


    public Problem(String problem, String problemCode, String answer,
                   int level, Date nextAvailable) {

        this.problem = problem;
        this.problemCode = problemCode;
        this.answer = answer;
        this.level = level;
        this.nextAvailable = nextAvailable;



    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getProblemCode() {
        return problemCode;
    }

    public void setProblemCode(String problemCode) {
        this.problemCode = problemCode;
    }

    public int getLessonNo() {
        return lessonNo;
    }

    public void setLessonNo(int lessonNo) {
        this.lessonNo = lessonNo;
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

    public Date getNextAvailable() {
        return nextAvailable;
    }

    public void setNextAvailable(Date nextAvailable) {
        this.nextAvailable = nextAvailable;
    }
}