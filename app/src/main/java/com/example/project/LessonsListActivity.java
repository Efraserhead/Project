package com.example.project;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import java.lang.ref.WeakReference;


public class LessonsListActivity extends AppCompatActivity {
    int categoryChoice, lessonId1, lessonId2, lessonId3, lessonId4;
    String title;
    TextView categoryTitle;
    LessonViewModel lessonViewModel;
    Button lessonButton1, lessonButton2, lessonButton3, lessonButton4, resources1, resources2, resources3, resources4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        categoryChoice = intent.getIntExtra("categoryChoice", 0);
        title = intent.getStringExtra("title");
        setContentView(R.layout.list_lessons);
        categoryTitle = findViewById(R.id.categoryTitle);
        categoryTitle.setText(title);
        lessonButton1 = (Button) findViewById(R.id.lessonButton1);
        lessonViewModel = new ViewModelProvider(this).get(LessonViewModel.class);

        lessonButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startLesson(lessonId1);
            }
        });
        lessonButton2 = (Button) findViewById(R.id.lessonButton2);
        lessonButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startLesson(lessonId2);
            }
        });
        lessonButton3 = (Button) findViewById(R.id.lessonButton3);
        lessonButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startLesson(lessonId3);
            }
        });
        lessonButton4 = (Button) findViewById(R.id.lessonButton4);
        lessonButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startLesson(lessonId4);
            }
        });
        resources1 = (Button) findViewById(R.id.resources1);
        resources1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startResourcesPage(lessonId1);
            }
        });
        resources2 = (Button) findViewById(R.id.resources2);
        resources2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startResourcesPage(lessonId2);
            }
        });
        resources3 = (Button) findViewById(R.id.resources3);
        resources3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startResourcesPage(lessonId3);
            }
        });
        resources4 = (Button) findViewById(R.id.resources4);
        resources4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startResourcesPage(lessonId4);
            }
        });
        loadLessonList();
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

    public void loadLessonList() {
        new LoadLessonsListAsyncTask(this).execute();
    }


    private static class LoadLessonsListAsyncTask extends AsyncTask<Void, Void, Cursor> {
        private final WeakReference<LessonsListActivity> lessonsListActivityWeakReference;

        public LoadLessonsListAsyncTask(LessonsListActivity activity) {
            lessonsListActivityWeakReference = new WeakReference<LessonsListActivity>(activity);
        }

        @Override
        protected Cursor doInBackground(Void... voids) {
            LessonsListActivity activity = lessonsListActivityWeakReference.get();
            if (activity == null || activity.isFinishing()) {
                return null;
            }
            return activity.lessonViewModel.getCategoryLessons(activity.categoryChoice);
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            LessonsListActivity activity = lessonsListActivityWeakReference.get();
            if (activity == null || activity.isFinishing()) {
                return;
            }
            super.onPostExecute(cursor);
            if (cursor.moveToFirst()) {
                boolean passCheck = (cursor.getInt(2) == 1);
                if (passCheck) {
                    activity.lessonButton1.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.done, 0);
                }
                activity.lessonId1 = cursor.getInt(0);
                activity.lessonButton1.setText(cursor.getString(3));
                activity.lessonButton1.setVisibility(View.VISIBLE);
                activity.resources1.setVisibility(View.VISIBLE);
                activity.resources2.setEnabled(passCheck);
                activity.lessonButton2.setEnabled(passCheck);
                if (cursor.moveToNext()) {
                    passCheck = (cursor.getInt(2) == 1);
                    if (passCheck) {
                        activity.lessonButton2.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.done, 0);
                    }
                    activity.lessonId2 = cursor.getInt(0);
                    activity.lessonButton2.setText(cursor.getString(3));
                    activity.lessonButton2.setVisibility(View.VISIBLE);
                    activity.resources2.setVisibility(View.VISIBLE);
                    activity.resources3.setEnabled(passCheck);
                    activity.lessonButton3.setEnabled(passCheck);
                    if (cursor.moveToNext()) {
                        passCheck = (cursor.getInt(2) == 1);
                        if (passCheck) {
                            activity.lessonButton3.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.done, 0);
                        }
                        activity.lessonId3 = cursor.getInt(0);
                        activity.lessonButton3.setText(cursor.getString(3));
                        activity.lessonButton3.setVisibility(View.VISIBLE);
                        activity.resources3.setVisibility(View.VISIBLE);
                        activity.resources4.setEnabled(passCheck);
                        activity.lessonButton4.setEnabled(passCheck);
                    }
                    if (cursor.moveToNext()) {
                        passCheck = (cursor.getInt(2) == 1);
                        if (passCheck) {
                            activity.lessonButton4.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.done, 0);
                        }
                        activity.lessonId4 = cursor.getInt(0);
                        activity.lessonButton4.setText(cursor.getString(3));
                        activity.lessonButton4.setVisibility(View.VISIBLE);
                        activity.resources4.setVisibility(View.VISIBLE);
                    }
                }
            }
            cursor.close();
        }
    }

    public void startLesson(int lessonChoice) {
        Intent intent = new Intent(this, LessonActivity.class);
        intent.putExtra("lessonChoice", lessonChoice);
        startActivity(intent);
    }

    public void startResourcesPage(int resourceChoice) {
        Intent intent = new Intent(this, AdditionalResourcesActivity.class);
        intent.putExtra("resourcesChoice", resourceChoice);
        startActivity(intent);
    }


}