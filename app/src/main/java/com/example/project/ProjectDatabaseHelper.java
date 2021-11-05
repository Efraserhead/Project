package com.example.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class ProjectDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "Project"; // the name of the database
    private static final int DB_VERSION = 1; // the version of the database

    ProjectDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_LESSON_TABLE = "CREATE TABLE " +
                Contract.LessonTable.TABLE_NAME + "(" +
                Contract.LessonTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Contract.LessonTable.COLUMN_NAME + " TEXT, " +
                Contract.LessonTable.COLUMN_LEVEL + " INTEGER, " +
                Contract.LessonTable.COLUMN_PASS + " INTEGER" +
                ")";

        db.execSQL(SQL_CREATE_LESSON_TABLE);


        insertLesson(db,"getting started",1,0);
        insertLesson(db,"variables",2,0);
        insertLesson(db,"data types",2,0);
        insertLesson(db,"operators",2,0);
        insertLesson(db,"strings",2,0);
        insertLesson(db,"control structures",3,0);
        insertLesson(db,"looping 1",3,0);
        insertLesson(db,"looping 2",3,0);
        insertLesson(db,"syntax",3,0);
        insertLesson(db,"data structures",4,0);
        insertLesson(db,"functions 1",4,0);
        insertLesson(db,"functions 2",4,0);
        insertLesson(db,"debugging",4,0);


        final String SQL_CREATE_QUIZ_TABLE = "CREATE TABLE " +
                Contract.QuizTable.TABLE_NAME + " ( " +
                Contract.QuizTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Contract.QuizTable.COLUMN_QUESTION + " TEXT, " +
                Contract.QuizTable.COLUMN_ANSWER1 + " TEXT," +
                Contract.QuizTable.COLUMN_ANSWER2 + " TEXT," +
                Contract.QuizTable.COLUMN_ANSWER3 + " TEXT," +
                Contract.QuizTable.COLUMN_CORRECT_ANSWER + " INTEGER," +
                Contract.QuizTable.COLUMN_LESSON_NUMBER + " INTEGER" +
                ")";

        db.execSQL(SQL_CREATE_QUIZ_TABLE);

        insertQuizQuestion(db,"the answer is 1","1","2","3",1,2);
        insertQuizQuestion(db,"the answer is 1","1","2","3",1,2);
        insertQuizQuestion(db,"the answer is 3","1","2","3",3,2);
        insertQuizQuestion(db,"the answer is 1","1","2","3",1,2);
        insertQuizQuestion(db,"the answer is 2","1","2","3",2,2);


        insertQuizQuestion(db,"the answer is love","blue","red","love",2,2);



        final String SQL_CREATE_PROBLEM_SCHEDULE ="CREATE TABLE " +
                Contract.ProblemSchedule.TABLE_NAME + "(" +
                Contract.ProblemSchedule._ID + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Contract.ProblemSchedule.COLUMN_PROBLEM + " TEXT," +
                Contract.ProblemSchedule.COLUMN_PROBLEM_CODE + " TEXT," +
                Contract.ProblemSchedule.COLUMN_ANSWER + " TEXT," +
                Contract.ProblemSchedule.COLUMN_GROUP + "INTEGER," +
                Contract.ProblemSchedule.COLUMN_DATE_DONE + " TEXT, " +
                Contract.ProblemSchedule.COLUMN_LESSON_NUMBER + " INTEGER" +")";




        db.execSQL(SQL_CREATE_PROBLEM_SCHEDULE);



    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }


    private static void insertLesson(SQLiteDatabase db, String name,
                                    int level, int pass) {
        ContentValues lessonValues = new ContentValues();
        lessonValues.put(Contract.LessonTable.COLUMN_NAME, name);
        lessonValues.put(Contract.LessonTable.COLUMN_LEVEL, level);
        lessonValues.put(Contract.LessonTable.COLUMN_PASS, pass);
        db.insert(Contract.LessonTable.TABLE_NAME, null, lessonValues);
    }

    private static void insertQuizQuestion(SQLiteDatabase db, String question, String answer1,
                                           String answer2, String answer3, int correctAnswer,
                                           int lessonNumber) {
        ContentValues quizValues = new ContentValues();
        quizValues.put(Contract.QuizTable.COLUMN_QUESTION,question);
        quizValues.put(Contract.QuizTable.COLUMN_ANSWER1,answer1);
        quizValues.put(Contract.QuizTable.COLUMN_ANSWER2,answer2);
        quizValues.put(Contract.QuizTable.COLUMN_ANSWER3,answer3);
        quizValues.put(Contract.QuizTable.COLUMN_CORRECT_ANSWER,correctAnswer);
        quizValues.put(Contract.QuizTable.COLUMN_LESSON_NUMBER,lessonNumber);
        db.insert(Contract.QuizTable.TABLE_NAME,null,quizValues);
    }


}

