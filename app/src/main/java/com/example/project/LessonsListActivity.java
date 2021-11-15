package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class LessonsListActivity extends AppCompatActivity {
    int categoryChoice;
    TextView categoryTitle;
    Button lessonButton1,lessonButton2,lessonButton3,lessonButton4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        categoryChoice = intent.getIntExtra("categoryChoice", 0);
        setContentView(R.layout.list_lessons);
        categoryTitle = findViewById(R.id.categoryTitle);
        lessonButton1 = (Button) findViewById(R.id.lessonButton1);
        lessonButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lessonChoiceOne();
            }
        });
        lessonButton2 = (Button) findViewById(R.id.lessonButton2);
        lessonButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                lessonChoiceTwo();
            }
        });
        lessonButton3 = (Button) findViewById(R.id.lessonButton3);
        lessonButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                lessonChoiceThree();
            }
        });
        lessonButton4 = (Button) findViewById(R.id.lessonButton4);
        lessonButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                lessonChoiceFour();
            }
        });
        switch (categoryChoice) {
            case 0:
                break;
            case 1:
                categoryTitle = findViewById(R.id.categoryTitle);
                categoryTitle.setText("GETTING STARTED");
                lessonButton1.setText("INTRODUCTION");
                lessonButton2.setVisibility(View.INVISIBLE);
                lessonButton3.setVisibility(View.INVISIBLE);
                lessonButton4.setVisibility(View.INVISIBLE);
                break;
            case 2:
                categoryTitle = findViewById(R.id.categoryTitle);
                categoryTitle.setText("SECTION ONE: DATA");
                lessonButton1.setText("VARIABLES");
                lessonButton2.setText("DATA TYPES");
                lessonButton3.setText("OPERATIONS");
                lessonButton4.setText("STRINGS");
                break;
            case 3:
                categoryTitle = findViewById(R.id.categoryTitle);
                categoryTitle.setText("SECTION TWO: CONTROL FLOW");
                lessonButton1.setText("CONTROL STRUCTURES");
                lessonButton2.setText("LOOPING PART 1");
                lessonButton3.setText("LOOPING PART 2");
                lessonButton4.setText("SYNTAX");
                break;
            case 4:
                categoryTitle = findViewById(R.id.categoryTitle);
                categoryTitle.setText("SECTION THREE: FUNCTIONALITY");
                lessonButton1.setText("DATA STRUCTURES");
                lessonButton2.setText("FUNCTIONS PART 1");
                lessonButton3.setText("FUNCTIONS PART 2");
                lessonButton4.setText("DEBUGGING");
                break;


        }

        lessonUnlock();
    }

    public void lessonUnlock() {

            Cursor cursor = ProjectDatabase.getInstance(this).lessonDao().getCategoryLessons(categoryChoice);
            if (cursor.moveToFirst()) {
                boolean passCheck = (cursor.getInt(3)==1);
                lessonButton2.setEnabled(passCheck);
                if (cursor.moveToNext()) {
                    passCheck = (cursor.getInt(3)==1);
                    lessonButton3.setEnabled(passCheck);
                    if(cursor.moveToNext()) {
                        passCheck = (cursor.getInt(3)==1);
                        lessonButton4.setEnabled(passCheck);
                    }
                }
            }
            cursor.close();
    }

    public void lessonChoiceOne() {
        Intent intent = new Intent(this, LessonActivity.class);
        switch(categoryChoice){
            case 0: break;
            case 1: intent.putExtra("lessonChoice",1); startActivity(intent);break;
            case 2:intent.putExtra("lessonChoice",2); startActivity(intent);break;
            case 3:intent.putExtra("lessonChoice",6); startActivity(intent);break;
            case 4:intent.putExtra("lessonChoice",10); startActivity(intent);break;
        }

    }
    public void lessonChoiceTwo() {
        Intent intent = new Intent(this, LessonActivity.class);
        switch(categoryChoice){
            case 0: break;
            case 1: intent.putExtra("lessonChoice",1); startActivity(intent);break;
            case 2:intent.putExtra("lessonChoice",3); startActivity(intent);break;
            case 3:intent.putExtra("lessonChoice",7); startActivity(intent);break;
            case 4:intent.putExtra("lessonChoice",11); startActivity(intent);break;
        }

    }
    public void lessonChoiceThree() {
        Intent intent = new Intent(this, LessonActivity.class);
        switch(categoryChoice){
            case 0: break;
            case 1: intent.putExtra("lessonChoice",1); startActivity(intent);break;
            case 2:intent.putExtra("lessonChoice",4); startActivity(intent);break;
            case 3:intent.putExtra("lessonChoice",8); startActivity(intent);break;
            case 4:intent.putExtra("lessonChoice",12); startActivity(intent);break;
        }

    }
    public void lessonChoiceFour() {
        Intent intent = new Intent(this, LessonActivity.class);
        switch(categoryChoice){
            case 0: break;
            case 1: intent.putExtra("lessonChoice",1); startActivity(intent);break;
            case 2:intent.putExtra("lessonChoice",5); startActivity(intent);break;
            case 3:intent.putExtra("lessonChoice",9); startActivity(intent);break;
            case 4:intent.putExtra("lessonChoice",13); startActivity(intent);break;
        }

    }

}