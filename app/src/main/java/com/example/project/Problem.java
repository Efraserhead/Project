package com.example.project;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "problem_table")
public class Problem {


    private String problem, answer, problemCode,lastCompleted;
    private int lessonNo, level;

    @PrimaryKey(autoGenerate = true)
    private int id;


    public Problem(int id, String problem, String problemCode, String answer,
                   int level, String lastCompleted) {
        this.id = id;
        this.problem = problem;
        this.problemCode = problemCode;
        this.answer = answer;
        this.lastCompleted = lastCompleted;
        this.level = level;


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

    public String getLastCompleted() {
        return lastCompleted;
    }

    public void setLastCompleted(String lastCompleted) {
        this.lastCompleted = lastCompleted;
    }
}