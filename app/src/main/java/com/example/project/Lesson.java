package com.example.project;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.ArrayList;
import java.util.Arrays;


@Entity(tableName = "lesson_table")
public class Lesson {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int categoryId;
    private int pass;
    private String name;


    @TypeConverters(LessonPagesConverter.class)
    private ArrayList<LessonPage> lessonPages;


    @TypeConverters(ResourcesListConverter.class)
    private ArrayList<String> resourceLinks;


    @Ignore
    public static final ArrayList<String> variablesResource = new ArrayList<>(Arrays.asList(
           "https://www.geeksforgeeks.org/","https://www.google.com/"
    ));


    @Ignore
    public static final ArrayList<LessonPage> introduction = new ArrayList<>(
            Arrays.asList(
            new LessonPage(R.string.lesson1_1,0),
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
            new LessonPage(R.string.lesson1_16,R.drawable.brain_2)));



    @Ignore
    public static final ArrayList<LessonPage> variables = new ArrayList<>(Arrays.asList(
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
            new LessonPage(R.string.lesson1_16,R.drawable.brain_2)));


    @Ignore
    public static final ArrayList<LessonPage> dataTypes = new ArrayList<>(Arrays.asList(
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
            new LessonPage(R.string.lesson1_16,R.drawable.brain_2)));

    @Ignore
    public static final ArrayList<LessonPage> operators = new ArrayList<>(Arrays.asList(
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
            new LessonPage(R.string.lesson1_16,R.drawable.brain_2)));

    @Ignore
    public static final ArrayList<LessonPage> stringsPages = new ArrayList<>(Arrays.asList(
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
            new LessonPage(R.string.lesson1_16,R.drawable.brain_2)));

    @Ignore
    public static final ArrayList<LessonPage> controlStructures = new ArrayList<>(Arrays.asList(
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
            new LessonPage(R.string.lesson1_16,R.drawable.brain_2)));

    @Ignore
    public static final ArrayList<LessonPage> looping = new ArrayList<>(Arrays.asList(
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
            new LessonPage(R.string.lesson1_16,R.drawable.brain_2)));

    @Ignore
    public static final ArrayList<LessonPage> functions = new ArrayList<>(Arrays.asList(
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
            new LessonPage(R.string.lesson1_16,R.drawable.brain_2)));

    @Ignore
    public static final ArrayList<LessonPage> OOP = new ArrayList<>(Arrays.asList(
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
            new LessonPage(R.string.lesson1_16,R.drawable.brain_2)));


    @Ignore
    public static final ArrayList<LessonPage> OOP2 = new ArrayList<>(Arrays.asList(
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
            new LessonPage(R.string.lesson1_16,R.drawable.brain_2)));


    @Ignore
    public static final ArrayList<LessonPage> dataStructures = new ArrayList<>(Arrays.asList(
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
            new LessonPage(R.string.lesson1_16,R.drawable.brain_2)));

    @Ignore
    public static final ArrayList<LessonPage> dataStructuresLoop = new ArrayList<>(Arrays.asList(
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
            new LessonPage(R.string.lesson1_16,R.drawable.brain_2)));

    @Ignore
    public static final ArrayList<LessonPage> syntax = new ArrayList<>(Arrays.asList(
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
            new LessonPage(R.string.lesson1_16,R.drawable.brain_2)));


    @Ignore
    public static final ArrayList<LessonPage> debugging = new ArrayList<>(Arrays.asList(
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
            new LessonPage(R.string.lesson1_16,R.drawable.brain_2)));

    @Ignore
    public Lesson(){}

    public Lesson(String name, int categoryId, int pass, ArrayList<LessonPage> lessonPages, ArrayList<String> resourceLinks) {
        this.categoryId = categoryId;
        this.pass = pass;
        this.name = name;
        this.lessonPages = lessonPages;
        this.resourceLinks = resourceLinks;

    }

    public ArrayList<String> getResourceLinks() {
        return resourceLinks;
    }

    public void setResourceLinks(ArrayList<String> resourceLinks) {
        this.resourceLinks = resourceLinks;
    }

    public ArrayList<LessonPage> getLessonPages() { return lessonPages; }

    public void setLessonPages(ArrayList<LessonPage> lessonPages) {
        this.lessonPages = lessonPages;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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

