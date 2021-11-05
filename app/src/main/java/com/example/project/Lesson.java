package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;


public class Lesson extends AppCompatActivity {

    int lessonCounter = 0;
    ArrayList<Integer> thisLessonText = new ArrayList<>();
    ArrayList<Integer> introductionText = new ArrayList<Integer>(Arrays.asList(R.string.lesson1_4));
    ArrayList<Integer> variableText = new ArrayList<Integer>(Arrays.asList(R.string.lesson1_1, R.string.lesson1_2, R.string.lesson1_3, R.string.lesson1_4, R.string.lesson1_5, R.string.lesson1_6,
            R.string.lesson1_7, R.string.lesson1_8, R.string.lesson1_9, R.string.lesson1_10, R.string.lesson1_11, R.string.lesson1_12, R.string.lesson1_13,
            R.string.lesson1_14, R.string.lesson1_15, R.string.lesson1_16));
    ArrayList<Integer> dataTypesText = new ArrayList<Integer>(Arrays.asList(R.string.lesson2_1,R.string.lesson2_2,R.string.lesson2_3,R.string.lesson2_4,R.string.lesson2_5,R.string.lesson2_6,R.string.lesson2_7,
            R.string.lesson2_8,R.string.lesson2_9,R.string.lesson2_10,R.string.lesson2_11,R.string.lesson2_12,R.string.lesson2_13,R.string.lesson2_14));
    ArrayList<Integer> operationsText = new ArrayList<Integer>();
    ArrayList<Integer> stringsText = new ArrayList<Integer>();
    ArrayList<Integer> loopingP1Text = new ArrayList<Integer>();
    ArrayList<Integer> loopingP2Text = new ArrayList<Integer>();
    ArrayList<Integer> controlStructuresText = new ArrayList<Integer>();
    ArrayList<Integer> syntaxText = new ArrayList<Integer>();
    ArrayList<Integer> dataStructuresText = new ArrayList<Integer>();
    ArrayList<Integer> functionsP1Text = new ArrayList<Integer>();
    ArrayList<Integer> functionP2Text = new ArrayList<>();
    ArrayList<Integer> debuggingText = new ArrayList<Integer>();


    public Lesson() { }

    public void chooseLesson(int lessonChoice) {
        switch(lessonChoice) {
            case 1:
                thisLessonText.clear();
                thisLessonText.addAll(introductionText);
                break;
            case 2:
                thisLessonText.clear();
                thisLessonText.addAll(variableText);
                break;
            case 3:
                thisLessonText.clear();
                thisLessonText.addAll(dataTypesText);
                break;
            case 4:
               thisLessonText.clear();
               thisLessonText.addAll(operationsText);
                break;
            case 5:
                thisLessonText.clear();
                thisLessonText.addAll(stringsText);
                break;

        }
    }


}

