package com.example.project;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.view.Menu;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class HomeActivity extends AppCompatActivity {
Button dataButton, flowButton, structuresButton, functionsButton, reviewButton;
TextView username,lessonProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        username = findViewById(R.id.username);
        lessonProgress = findViewById(R.id.progressText);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String userNameText = sharedPreferences.getString("username","username");
        int lessonProgressNo = sharedPreferences.getInt("lessonProgress",0);
        lessonProgress.setText("Lessons completed: "+lessonProgressNo);
        username.setText(userNameText);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        dataButton = findViewById(R.id.dataSectionButton);
        dataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                chooseCategoryOne();
            }
        });
        flowButton = findViewById(R.id.flowbutton);
        flowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                chooseCategoryTwo();
            }
        });
        structuresButton = findViewById(R.id.structuresButton);
        structuresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                chooseCategoryThree();
            }
        });
        functionsButton = findViewById(R.id.functionsButton);
        functionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                chooseCategoryFour();
            }
        });
        reviewButton = findViewById(R.id.problemButton);
        reviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enterProblems();
            }
        });

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

    public void chooseCategoryOne() {
        Intent intent = new Intent(this, AdditionalResourcesActivity.class);
        intent.putExtra("categoryChoice",1);
        startActivity(intent);
    }public void chooseCategoryTwo() {
        Intent intent = new Intent(this, LessonsListActivity.class);
        intent.putExtra("categoryChoice",2);
        startActivity(intent);
    }
    public void chooseCategoryThree() {
        Intent intent = new Intent(this, LessonsListActivity.class);
        intent.putExtra("categoryChoice",3);
        startActivity(intent);
    }
    public void chooseCategoryFour() {
        Intent intent = new Intent(this, LessonsListActivity.class);
        intent.putExtra("categoryChoice",4);
        startActivity(intent);
    }

    public void enterProblems() {
        Intent intent = new Intent(this,ProblemActivity.class);
        startActivity(intent);
    }


}