package com.example.project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    Button signUpButton;
    String userName;
    EditText makeUserName;
    Boolean signedUp;
    LessonViewModel lessonViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lessonViewModel = new ViewModelProvider(this).get(LessonViewModel.class);
        signUpButton = (Button) findViewById(R.id.signUpButton);
        makeUserName = (EditText) findViewById(R.id.makeUsername);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {signUpButton(); }
        });

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        signedUp = sharedPreferences.getBoolean("signedUp",false);
        if(signedUp) {
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
            sharedPreferences.edit().putString("username",userName).apply();
            lessonViewModel.Insert(new Lesson("getting started", 1, 0, Lesson.introduction));
            lessonViewModel.Insert(new Lesson("variables", 2, 0,Lesson.variables));
            lessonViewModel.Insert(new Lesson("data types", 2, 0,Lesson.dataTypes));
            lessonViewModel.Insert(new Lesson("operators", 2, 0, Lesson.operators));
            lessonViewModel.Insert(new Lesson("strings", 2, 0, Lesson.stringsPages));
            lessonViewModel.Insert(new Lesson("control structures", 3, 0, Lesson.controlStructures));
            lessonViewModel.Insert(new Lesson("looping 1", 3, 0, Lesson.looping1));
            lessonViewModel.Insert(new Lesson("looping 2", 3, 0, Lesson.looping2));
            lessonViewModel.Insert(new Lesson("syntax", 3, 0, Lesson.syntax));
            lessonViewModel.Insert(new Lesson("data structures", 4, 0, Lesson.dataStructures));
            lessonViewModel.Insert(new Lesson("functions 1", 4, 0, Lesson.functions1));
            lessonViewModel.Insert(new Lesson("functions 2", 4, 0, Lesson.functions2));
            lessonViewModel.Insert(new Lesson("debugging", 4, 0, Lesson.debugging));
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra("lessonChoice",1);
            startActivity(intent);
        }


    }

/*
    public void makeLesson() {
ProjectDatabase.getInstance(this).lessonDao().insert(new Lesson());
        ProjectDatabase.getInstance(this).lessonDao().insert(new Lesson());
        ProjectDatabase.getInstance(this).lessonDao().insert(new Lesson());
        ProjectDatabase.getInstance(this).lessonDao().insert(new Lesson());
        ProjectDatabase.getInstance(this).lessonDao().insert(new Lesson());
        ProjectDatabase.getInstance(this).lessonDao().insert(new Lesson());
        ProjectDatabase.getInstance(this).lessonDao().insert(new Lesson());
        ProjectDatabase.getInstance(this).lessonDao().insert(new Lesson());
        ProjectDatabase.getInstance(this).lessonDao().insert(new Lesson());
        ProjectDatabase.getInstance(this).lessonDao().insert(new Lesson());
    }

        new Lesson("getting started", 1, 0, Lesson.introduction);
        new Lesson("variables", 2, 0,Lesson.variables);
        new Lesson("data types", 2, 0,Lesson.dataTypes);
        new Lesson( "operators", 2, 0);
        new Lesson("strings", 2, 0);
        insertLesson("control structures", 3, 0);
        insertLesson("looping 1", 3, 0);
        insertLesson("looping 2", 3, 0);
        insertLesson("syntax", 3, 0);
        insertLesson("data structures", 4, 0);
        insertLesson("functions 1", 4, 0);
        insertLesson("functions 2", 4, 0);
        insertLesson("debugging", 4, 0);

    } **/
}


