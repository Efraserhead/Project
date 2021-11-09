package com.example.project;

import android.provider.BaseColumns;

public class Contract {

    private Contract() {}


    public static class LessonTable implements BaseColumns {
        public static final String TABLE_NAME = "LESSON";
        public static final String COLUMN_NAME ="NAME";
        public static final String COLUMN_LEVEL ="LEVEL";
        public static final String COLUMN_PASS ="PASS";
    }

    public static class QuizTable implements BaseColumns {
        public static final String TABLE_NAME ="questions_table";
        public static final String COLUMN_QUESTION ="question";
        public static final String COLUMN_ANSWER1 ="answer1";
        public static final String COLUMN_ANSWER2 ="answer2";
        public static final String COLUMN_ANSWER3 ="answer3";
        public static final String COLUMN_CORRECT_ANSWER ="correct_answer";
        public static final String COLUMN_LESSON_NUMBER ="lesson_number";
    }


    public static class ProblemSchedule implements BaseColumns {

        public static final String TABLE_NAME ="schedule_table";
        public static final String COLUMN_PROBLEM ="problem";
        public static final String COLUMN_PROBLEM_CODE ="problem_code";
        public static final String COLUMN_ANSWER ="answer";
        public static final String COLUMN_GROUP ="level";
        public static final String COLUMN_DATE_DONE ="date_done";
        public static final String COLUMN_LESSON_NUMBER ="lesson_number";

    }






}
