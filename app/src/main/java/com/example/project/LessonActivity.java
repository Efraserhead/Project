package com.example.project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Objects;



public class LessonActivity extends AppCompatActivity {

    private int lessonChoice;
    private int lessonSize;
    private ArrayList<LessonPage> lessonPages;
    private ViewPager2 viewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lesson_layout);
        Intent intent = getIntent();
        lessonChoice = intent.getIntExtra("lessonChoice",1);
        setUpLesson();
        setUpViewPager();

    }


    public void setUpLesson() {
        Lesson thisLesson = ProjectDatabase.getInstance(this).lessonDao().getLesson(lessonChoice);
        lessonPages = thisLesson.getThisLesson();
    }




    public void setUpViewPager() {
        LessonPageAdapter lessonPageAdapter = new LessonPageAdapter(lessonPages);
        viewPager = findViewById(R.id.pager);
        viewPager.setAdapter(lessonPageAdapter);
        lessonSize = viewPager.getAdapter().getItemCount();
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
        new AlertDialog.Builder(LessonActivity.this)
                    .setTitle("Lesson finished")
                    .setMessage("click ok to check your understanding")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();

                    }
                }).setPositiveButton("ok", new DialogInterface.OnClickListener() {
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
