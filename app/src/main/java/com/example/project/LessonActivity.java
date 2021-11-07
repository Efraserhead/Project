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


public class LessonActivity extends AppCompatActivity {

    private Button finishButton;
    private int lessonChoice;
    private LessonData lessonData;
    private LessonData[] lesson;
    private ViewPager2 viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lesson_layout);
        lessonData = new LessonData();
        Intent intent = getIntent();
        lessonChoice = intent.getIntExtra("lessonChoice",2);
        lesson = lessonData.getLesson(lessonChoice);
        LessonPageAdapter lessonPageAdapter = new LessonPageAdapter(lesson);
        viewPager = findViewById(R.id.pager);
        viewPager.setAdapter(lessonPageAdapter);


    }



    public void finish() {


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


        ViewPager2.OnPageChangeCallback pageChangeCallback = new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if(lesson.length == position) {

                }
            }
        };




    public void finishLesson() {
        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra("lessonChoice",lessonChoice);
        startActivity(intent);

    }




}
