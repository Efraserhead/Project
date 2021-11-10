package com.example.project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager2.widget.ViewPager2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import java.util.Arrays;
import java.util.List;


public class LessonActivity extends AppCompatActivity {

    private int lessonChoice;
    private int lessonSize;
    private Lesson thisLesson;
    private List<LessonPage> lessonPages;
    private ViewPager2 viewPager;
    private LessonViewModel lessonViewModel;
    private ProjectDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lesson_layout);
        Intent intent = getIntent();
        lessonChoice = intent.getIntExtra("lessonChoice",1);
        //lessonViewModel = ViewModelProviders.of(this).get(LessonViewModel.class);
        //db = ProjectDatabase.getInstance(this);
        //thisLesson = db.lessonDao().getLesson(lessonChoice);
        thisLesson = new Lesson();
        setThisLesson(lessonChoice);
        setUpViewPager();

    }

    public void setThisLesson(int lessonChoice) {
        switch (lessonChoice) {
            case 1:
                thisLesson.setThisLesson(Lesson.introduction);
                break;
            case 2:
                thisLesson.setThisLesson(Lesson.variables);
            case 3:
                thisLesson.setThisLesson(Lesson.dataTypes);
            case 4:
                thisLesson.setThisLesson(Lesson.operators);
            case 5:
                thisLesson.setThisLesson(Lesson.stringsPages);
            case 6:
                thisLesson.setThisLesson(Lesson.controlStructures);
            case 7:
                thisLesson.setThisLesson(Lesson.looping1);
            case 8:
                thisLesson.setThisLesson(Lesson.looping2);
            case 9:
                thisLesson.setThisLesson(Lesson.syntax);
            case 10:
                thisLesson.setThisLesson(Lesson.dataStructures);
            case 11:
                thisLesson.setThisLesson(Lesson.functions1);
            case 12:
                thisLesson.setThisLesson(Lesson.functions2);
            case 13:
                thisLesson.setThisLesson(Lesson.debugging);

        }
        lessonPages = thisLesson.getThisLesson();
    }



    public void setUpViewPager() {

        LessonPageAdapter lessonPageAdapter = new LessonPageAdapter(lessonPages);
        viewPager = findViewById(R.id.pager);
        viewPager.setAdapter(lessonPageAdapter);
        lessonSize= viewPager.getAdapter().getItemCount();
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                if(position==lessonSize-1) {
                    finish();
                }
                super.onPageSelected(position);
            }
        });
    }




    public void finish() {
        new AlertDialog.Builder(this)
                    .setTitle("Lesson finished")
                    .setMessage("click continue to check your understanding")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();

                    }
                }).setPositiveButton("continue", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            startTest();
                        }
                })
                .create().show();

    }


    public void startTest() {
        Intent intent = new Intent(this, TestActivity.class);
        intent.putExtra("lessonChoice",lessonChoice);
        startActivity(intent);

    }




}
