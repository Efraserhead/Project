package com.example.project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class LessonActivity extends AppCompatActivity {

    private ImageView lessonImage;
    private TextView lessonTxt1, lessonTxt2, lessonTxt3;
    private Button prevButton, nextButton;
    private int lessonChoice;
    private int lessonCounter;
    private Lesson lesson;
    private LessonPage lessonPage;
    private ViewPager2 viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lesson_layout);
        lessonPage = new LessonPage();
        lesson = new Lesson();
        lessonCounter = 0;
        lessonImage = (ImageView) findViewById(R.id.lessonImage);
        Intent intent = getIntent();
        lessonChoice = intent.getIntExtra("lessonChoice",1);
        lesson.chooseLesson(lessonChoice);
        LessonPageAdapter lessonPageAdapter = new LessonPageAdapter(LessonPage.variables);
        viewPager = findViewById(R.id.pager);
        viewPager.setAdapter(lessonPageAdapter);




    }

    public void nextButton() {

        if (lessonCounter < lesson.thisLessonText.size()) {
            nextStep(); //move through lesson
        } else {
            //launch test

            new AlertDialog.Builder(this)
                    .setTitle("Lesson finished")
                    .setMessage("click continue to check your understanding")
                    .setPositiveButton("continue", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            finishLesson();

                        }
                    })
                    .create().show();

        }
    }



    public void finishLesson() {
        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra("lessonChoice",lessonChoice);
        startActivity(intent);

    }

    public void nextStep() {

        if ((lessonTxt1.getVisibility() == View.INVISIBLE)
                & (lessonTxt2.getVisibility() == View.INVISIBLE)
                & (lessonTxt3.getVisibility() == View.INVISIBLE)) {
            lessonTxt1.setVisibility(View.VISIBLE);
            lessonTxt1.setText(lesson.thisLessonText.get(lessonCounter));
            lessonCounter++;
        } else if ((lessonTxt1.getVisibility() == View.VISIBLE)
                & (lessonTxt2.getVisibility() == View.INVISIBLE)
                & (lessonTxt3.getVisibility() == View.INVISIBLE)) {
            lessonTxt2.setVisibility(View.VISIBLE);
            lessonTxt2.setText(lesson.thisLessonText.get(lessonCounter));
            lessonCounter++;
        } else if ((lessonTxt1.getVisibility() == View.VISIBLE)
                & (lessonTxt2.getVisibility() == View.VISIBLE)
                & (lessonTxt3.getVisibility() == View.INVISIBLE)) {
            lessonTxt3.setVisibility(View.VISIBLE);
            lessonTxt3.setText(lesson.thisLessonText.get(lessonCounter));
            lessonImage.setVisibility(View.VISIBLE);
            lessonImage.setImageResource(R.drawable.container);
            lessonCounter++;
        } else if ((lessonTxt1.getVisibility() == View.VISIBLE)
                & (lessonTxt2.getVisibility() == View.VISIBLE)
                & (lessonTxt3.getVisibility() == View.VISIBLE)) {
            lessonTxt2.setVisibility(View.INVISIBLE);
            lessonTxt3.setVisibility(View.INVISIBLE);
            lessonTxt1.setText(lesson.thisLessonText.get(lessonCounter));
            lessonCounter++;
        }
    }


}
