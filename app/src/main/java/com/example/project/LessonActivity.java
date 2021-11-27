package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        lessonViewModel = new ViewModelProvider(this).get(LessonViewModel.class);
        new ChooseLessonAsyncTask(this).execute();

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the app bar.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                return true;
            case R.id.settingsTab:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            case R.id.exitTab:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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

                        activity.finishLesson();

                    }
                    super.onPageSelected(position);

                }
            });
        }
    }


    public void finishLesson() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        CustomDialogFragment customDialogFragment = CustomDialogFragment.newInstance("Lesson finished","press ok to finish lesson");
        customDialogFragment.show(fragmentManager,"lesson finished");

    }



}
