package com.example.project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;



public class LessonActivity extends AppCompatActivity {

    private int lessonChoice;
    private int lessonSize;
    private LessonData lessonData;
    private LessonData[] lesson;
    private ViewPager2 viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lesson_layout);
        Intent intent = getIntent();
        lessonChoice = intent.getIntExtra("lessonChoice",1);
        setUpLesson(lessonChoice);

    }

    public void setUpLesson(int lessonChoice) {
        lessonData = new LessonData();
        switch (lessonChoice) {
            case 1:
                break;
            case 2:
                lessonData.setThisLesson(LessonData.variables);
        }
        lesson = lessonData.getThisLesson();
        LessonPageAdapter lessonPageAdapter = new LessonPageAdapter(lesson);
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
