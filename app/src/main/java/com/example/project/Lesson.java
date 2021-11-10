package com.example.project;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "lesson_table")
public class Lesson {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int level;
    private int pass;
    private String name;


    @Ignore
    private LessonPage[] thisLesson;


    @Ignore
    public static final LessonPage[] introduction = {
            new LessonPage(R.string.lesson1_1,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_2, R.drawable.brain_2),
            new LessonPage(R.string.lesson1_3,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_4,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_5,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_6,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_7,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_8,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_9,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_10,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_11,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_12,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_13,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_14,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_15,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_16,R.drawable.brain_2)};


    @Ignore
    public static final LessonPage[] variables = {
            new LessonPage(R.string.lesson1_1,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_2, R.drawable.brain_2),
            new LessonPage(R.string.lesson1_3,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_4,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_5,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_6,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_7,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_8,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_9,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_10,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_11,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_12,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_13,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_14,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_15,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_16,R.drawable.brain_2)};

    @Ignore
    public static final LessonPage[] dataTypes = {
            new LessonPage(R.string.lesson1_1,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_2, R.drawable.brain_2),
            new LessonPage(R.string.lesson1_3,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_4,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_5,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_6,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_7,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_8,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_9,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_10,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_11,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_12,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_13,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_14,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_15,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_16,R.drawable.brain_2)};

    @Ignore
    public static final LessonPage[] operators = {
            new LessonPage(R.string.lesson1_1,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_2, R.drawable.brain_2),
            new LessonPage(R.string.lesson1_3,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_4,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_5,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_6,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_7,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_8,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_9,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_10,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_11,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_12,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_13,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_14,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_15,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_16,R.drawable.brain_2)};

    @Ignore
    public static final LessonPage[] stringsPages = {
            new LessonPage(R.string.lesson1_1,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_2, R.drawable.brain_2),
            new LessonPage(R.string.lesson1_3,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_4,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_5,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_6,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_7,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_8,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_9,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_10,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_11,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_12,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_13,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_14,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_15,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_16,R.drawable.brain_2)};

    @Ignore
    public static final LessonPage[] controlStructures = {
            new LessonPage(R.string.lesson1_1,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_2, R.drawable.brain_2),
            new LessonPage(R.string.lesson1_3,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_4,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_5,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_6,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_7,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_8,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_9,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_10,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_11,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_12,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_13,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_14,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_15,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_16,R.drawable.brain_2)};

    @Ignore
    public static final LessonPage[] looping1 = {
            new LessonPage(R.string.lesson1_1,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_2, R.drawable.brain_2),
            new LessonPage(R.string.lesson1_3,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_4,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_5,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_6,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_7,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_8,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_9,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_10,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_11,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_12,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_13,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_14,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_15,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_16,R.drawable.brain_2)};

    @Ignore
    public static final LessonPage[] looping2 = {
            new LessonPage(R.string.lesson1_1,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_2, R.drawable.brain_2),
            new LessonPage(R.string.lesson1_3,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_4,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_5,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_6,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_7,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_8,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_9,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_10,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_11,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_12,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_13,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_14,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_15,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_16,R.drawable.brain_2)};

    @Ignore
    public static final LessonPage[] syntax = {
            new LessonPage(R.string.lesson1_1,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_2, R.drawable.brain_2),
            new LessonPage(R.string.lesson1_3,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_4,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_5,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_6,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_7,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_8,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_9,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_10,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_11,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_12,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_13,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_14,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_15,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_16,R.drawable.brain_2)};


    @Ignore
    public static final LessonPage[] dataStructures = {
            new LessonPage(R.string.lesson1_1,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_2, R.drawable.brain_2),
            new LessonPage(R.string.lesson1_3,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_4,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_5,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_6,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_7,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_8,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_9,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_10,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_11,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_12,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_13,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_14,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_15,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_16,R.drawable.brain_2)};

    @Ignore
    public static final LessonPage[] functions1 = {
            new LessonPage(R.string.lesson1_1,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_2, R.drawable.brain_2),
            new LessonPage(R.string.lesson1_3,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_4,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_5,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_6,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_7,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_8,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_9,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_10,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_11,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_12,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_13,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_14,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_15,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_16,R.drawable.brain_2)};

    @Ignore
    public static final LessonPage[] functions2 = {
            new LessonPage(R.string.lesson1_1,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_2, R.drawable.brain_2),
            new LessonPage(R.string.lesson1_3,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_4,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_5,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_6,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_7,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_8,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_9,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_10,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_11,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_12,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_13,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_14,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_15,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_16,R.drawable.brain_2)};


    @Ignore
    public static final LessonPage[] debugging = {
            new LessonPage(R.string.lesson1_1,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_2, R.drawable.brain_2),
            new LessonPage(R.string.lesson1_3,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_4,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_5,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_6,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_7,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_8,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_9,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_10,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_11,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_12,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_13,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_14,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_15,R.drawable.brain_2),
            new LessonPage(R.string.lesson1_16,R.drawable.brain_2)};

    public Lesson(){}


    public LessonPage[] getThisLesson() {
        return thisLesson;
    }

    public void setThisLesson(LessonPage[] thisLesson) {
        this.thisLesson = thisLesson;
    }

    public Lesson(String name, int level, int pass) {
        this.level = level;
        this.pass = pass;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPass() {
        return pass;
    }

    public void setPass(int pass) {
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

