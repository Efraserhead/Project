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
    private Lesson lesson;
    private LessonPage[] lessonPages;
    private ViewPager2 viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lesson_layout);
        Intent intent = getIntent();
        lessonChoice = intent.getIntExtra("lessonChoice",1);
        setUpLesson(lessonChoice);
        setUpViewPager();

    }

    public void setUpLesson(int lessonChoice) {
        switch (lessonChoice) {
            case 1:
                lesson.setThisLesson(Lesson.introduction);
                break;
            case 2:
                lesson.setThisLesson(Lesson.variables);
            case 3:
                lesson.setThisLesson(Lesson.dataTypes);
            case 4:
                lesson.setThisLesson(Lesson.operators);
            case 5:
                lesson.setThisLesson(Lesson.stringsPages);
            case 6:
                lesson.setThisLesson(Lesson.controlStructures);
            case 7:
                lesson.setThisLesson(Lesson.looping1);
            case 8:
                lesson.setThisLesson(Lesson.looping2);
            case 9:
                lesson.setThisLesson(Lesson.syntax);
            case 10:
                lesson.setThisLesson(Lesson.dataStructures);
            case 11:
                lesson.setThisLesson(Lesson.functions1);
            case 12:
                lesson.setThisLesson(Lesson.functions2);
            case 13:
                lesson.setThisLesson(Lesson.debugging);

        }
        lessonPages = lesson.getThisLesson();

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
