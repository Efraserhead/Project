package com.example.project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.lang.ref.WeakReference;


public class MainActivity extends AppCompatActivity {

    Button signUpButton;
    String userName;
    EditText makeUserName;
    Boolean signedUp;
    LessonViewModel lessonViewModel;
    CategoryViewModel categoryViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lessonViewModel = new ViewModelProvider(this).get(LessonViewModel.class);
        categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        signUpButton = (Button) findViewById(R.id.signUpButton);
        makeUserName = (EditText) findViewById(R.id.makeUsername);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUpButton();
            }
        });

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        signedUp = sharedPreferences.getBoolean("signedUp", false);
        if (signedUp) {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);

        }

    }


    public void signUpButton() {
        userName = makeUserName.getText().toString().toLowerCase().trim();
        if (TextUtils.isEmpty(userName)) {
            new AlertDialog.Builder(this)
                    .setTitle("Enter a username")
                    .setMessage("click ok to exit and enter a username")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .create().show();
        } else {

            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
            sharedPreferences.edit().putBoolean("signedUp", true).apply();
            sharedPreferences.edit().putString("username", userName).apply();
            new PrepopulateDataBaseAsyncTask(this).execute();
        }


    }

    public static class PrepopulateDataBaseAsyncTask extends AsyncTask<Void, Void, Void> {
        private final WeakReference<MainActivity> mainActivityWeakReference;

        public PrepopulateDataBaseAsyncTask(MainActivity activity) {
            mainActivityWeakReference = new WeakReference<MainActivity>(activity);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            MainActivity activity = mainActivityWeakReference.get();
            if (activity == null || activity.isFinishing()) {
                return null;
            }
            activity.categoryViewModel.insert(new Category("INTRODUCTION", "getting started", "", "", "", 1));
            activity.categoryViewModel.insert(new Category("PART I", "Variables", "Data types", "Operators", "Strings", 0));
            activity.categoryViewModel.insert(new Category("PART II", "Control structures", "Looping", "functions", "", 0));
            activity.categoryViewModel.insert(new Category("PART III", "OOP", "Data Structures", "Looping through structures", "", 0));
            activity.categoryViewModel.insert(new Category("PART IV", "Syntax", "Debugging", "", "", 0));
            activity.lessonViewModel.insert(new Lesson("getting started", 1, 0, Lesson.introduction));
            activity.lessonViewModel.insert(new Lesson("variables", 2, 0, Lesson.variables));
            activity.lessonViewModel.insert(new Lesson("data types", 2, 0, Lesson.dataTypes));
            activity.lessonViewModel.insert(new Lesson("operators", 2, 0, Lesson.operators));
            activity.lessonViewModel.insert(new Lesson("strings", 2, 0, Lesson.stringsPages));
            activity.lessonViewModel.insert(new Lesson("control structures", 3, 0, Lesson.controlStructures));
            activity.lessonViewModel.insert(new Lesson("looping ", 3, 0, Lesson.looping));
            activity.lessonViewModel.insert(new Lesson("functions", 3, 0, Lesson.functions));
            activity.lessonViewModel.insert(new Lesson("OOP", 4, 0, Lesson.OOP));
            activity.lessonViewModel.insert(new Lesson("OOP II", 4, 0, Lesson.OOP2));
            activity.lessonViewModel.insert(new Lesson("Data structures", 4, 0, Lesson.dataStructures));
            activity.lessonViewModel.insert(new Lesson("data structures II", 4, 0, Lesson.dataStructuresLoop));
            activity.lessonViewModel.insert(new Lesson("syntax", 5, 0, Lesson.syntax));
            activity.lessonViewModel.insert(new Lesson("debugging", 5, 0, Lesson.debugging));

            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            MainActivity activity = mainActivityWeakReference.get();
            if (activity == null || activity.isFinishing()) {
                return;
            }
            Intent intent = new Intent(activity, LessonActivity.class);
            intent.putExtra("lessonChoice", 1);
            activity.startActivity(intent);
        }
    }
}


