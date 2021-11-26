package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import java.lang.ref.WeakReference;
import java.util.ArrayList;


public class LessonActivity extends AppCompatActivity implements CustomDialogFragment.DialogListener {

    private int lessonChoice;
    private int lessonSize;
    private int lessonPass;
    private ArrayList<LessonPage> lessonPages;
    private ViewPager2 viewPager;
    private LessonPageAdapter lessonPageAdapter;
    private LessonViewModel lessonViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lesson_layout);
        Intent intent = getIntent();
        lessonChoice = intent.getIntExtra("lessonChoice",1);
        viewPager = findViewById(R.id.pager);
        lessonViewModel = new ViewModelProvider(this).get(LessonViewModel.class);
        new ChooseLessonAsyncTask(this).execute();

    }

    @Override
    public void getButtonAction(boolean buttonAction) {
        if(buttonAction) {
            Intent intent = new Intent(this, TestActivity.class);
            intent.putExtra("lessonChoice",lessonChoice);
            intent.putExtra("lesson pass",lessonPass);
            startActivity(intent);
        }
    }

    private static class ChooseLessonAsyncTask extends AsyncTask<Void,Void,Lesson> {
        private final WeakReference<LessonActivity> lessonActivityWeakReference;

        public ChooseLessonAsyncTask(LessonActivity activity) {
            lessonActivityWeakReference = new WeakReference<LessonActivity>(activity);
        }

        @Override
        protected Lesson doInBackground(Void... voids) {
            LessonActivity activity = lessonActivityWeakReference.get();
            if (activity == null || activity.isFinishing()) {
                return null;
            }
            return activity.lessonViewModel.getLesson(activity.lessonChoice);
        }

        @Override
        protected void onPostExecute(Lesson lesson) {
            super.onPostExecute(lesson);
            LessonActivity activity = lessonActivityWeakReference.get();
            if (activity == null || activity.isFinishing()) {
                return;
            }
            activity.lessonPages = lesson.getLessonPages();
            activity.lessonPass = lesson.getPass();
            activity.lessonPageAdapter = new LessonPageAdapter(activity.lessonPages);
            activity.viewPager.setAdapter(activity.lessonPageAdapter);
            activity.lessonSize = activity.viewPager.getAdapter().getItemCount();
            activity.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageSelected(int position) {
                    if(position==activity.lessonSize-1) {

                        activity.finish();

                    }
                    super.onPageSelected(position);

                }
            });
        }
    }


    public void finish() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        CustomDialogFragment customDialogFragment = CustomDialogFragment.newInstance("Lesson finished","press ok to finish lesson");
        customDialogFragment.show(fragmentManager,"lesson finished");

    }



}
