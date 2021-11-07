package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;


import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class TestActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private int lessonChoice;
    private int testCounter;
    private int score;
    private int lessonPass;
    private TextView questionText;
    private RadioGroup radioAnswers;
    private RadioButton answer1,answer2,answer3;
    private Button submit;
    private ArrayList<Question> questions = new ArrayList<>();





    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_);
        questionText = (TextView) findViewById(R.id.questionText);
        radioAnswers = (RadioGroup) findViewById(R.id.radio_group_answers);
        answer1 = (RadioButton) findViewById(R.id.radio_answer1);
        answer2 = (RadioButton) findViewById(R.id.radio_answer2);
        answer3 = (RadioButton) findViewById(R.id.radio_answer3);
        Intent intent = getIntent();
        lessonChoice = intent.getIntExtra("lessonChoice",1);
        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitAnswer();
            }
        });

        loadQuizQuestions();
        getLessonPass();

    }

    public void nextQuestion() {
        if (testCounter < questions.size()) {
            radioAnswers.clearCheck();
            questionText.setText(questions.get(testCounter).getQuestion());
            answer1.setText(questions.get(testCounter).getAnswer1());
            answer2.setText(questions.get(testCounter).getAnswer2());
            answer3.setText(questions.get(testCounter).getAnswer3());
        }
        else {
            radioAnswers.clearCheck();
            questionText.setText(R.string.hyperlink);
            questionText.setMovementMethod(LinkMovementMethod.getInstance());
            radioAnswers.setVisibility(View.INVISIBLE);
            submit.setText("FINISH");
        }
    }

    public void submitAnswer() {

         if (radioAnswers.getCheckedRadioButtonId() == -1) {
             if (testCounter >= questions.size()) {
                 endTest();
             }
             else {
                 Toast toast = Toast.makeText(this,
                         "select one answer",
                         Toast.LENGTH_SHORT);
                 toast.show();
             }
         }
         else {
             RadioButton selectedButton = findViewById(radioAnswers.getCheckedRadioButtonId());
             int selectedAnswer = 1;
             selectedAnswer += radioAnswers.indexOfChild(selectedButton);
             int correctAnswer = questions.get(testCounter).getCorrectAnswer();
             if(selectedAnswer == correctAnswer) {
                 score++;
                 testCounter++;
                 nextQuestion();
                 Toast toast = Toast.makeText(this,
                         "correct!",
                         Toast.LENGTH_SHORT);
                 toast.show();
             }
             else {
                 testCounter++;
                 nextQuestion();
                 Toast toast = Toast.makeText(this,
                         "incorrect",
                         Toast.LENGTH_SHORT);
                 toast.show();
             }
         }
    }

            public void endTest() {
             //update database and pass lesson
             if(score >= 3) {
                 updateLesson();
                 Toast toast = Toast.makeText(this,
                         "Your score is " + score + ". You have passed this lesson",
                         Toast.LENGTH_SHORT);
                 toast.show();
             } else {
                 Toast toast = Toast.makeText(this,
                         "Your score is " + score + " You need to work through the lesson again",
                         Toast.LENGTH_SHORT);
                 toast.show();
             }

             Intent intent = new Intent(this, HomeActivity.class);
             startActivity(intent);
         }

    public void loadQuizQuestions() {
        try {
            SQLiteOpenHelper projectDatabaseHelper = new ProjectDatabaseHelper(TestActivity.this);
            db = projectDatabaseHelper.getReadableDatabase();
            Cursor cursor = db.query(Contract.QuizTable.TABLE_NAME,new String[] {Contract.QuizTable.COLUMN_QUESTION,
            Contract.QuizTable.COLUMN_ANSWER1,Contract.QuizTable.COLUMN_ANSWER2,Contract.QuizTable.COLUMN_ANSWER3,
            Contract.QuizTable.COLUMN_CORRECT_ANSWER},Contract.QuizTable.COLUMN_LESSON_NUMBER + "=?",new String[] {Integer.toString(lessonChoice)},null,
            null,null);
           if (cursor.moveToFirst()) {
                do {
                   Question question = new Question(cursor.getString(0),cursor.getString(1)
                            ,cursor.getString(2),cursor.getString(3),cursor.getInt(4));
                    questions.add(question);
                }while(cursor.moveToNext());
            }
            cursor.close();
            db.close();
            questionText.setText(questions.get(0).getQuestion());
            answer1.setText(questions.get(0).getAnswer1());
            answer2.setText(questions.get(0).getAnswer2());
            answer3.setText(questions.get(0).getAnswer3());
        }  catch(SQLiteException e) {
            Toast toast = Toast.makeText(TestActivity.this,
                    "Database unavailable4",
                    Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void updateLesson() {
        if (lessonPass == 0) {
            try {
                SQLiteOpenHelper lessonDatabaseHelper = new ProjectDatabaseHelper(this);
                db = lessonDatabaseHelper.getWritableDatabase();
                ContentValues passValues = new ContentValues();
                passValues.put("PASS", 1);
                db.update(Contract.LessonTable.TABLE_NAME, passValues, Contract.LessonTable._ID + "= ?",
                        new String[]{Integer.toString(lessonChoice)});
                updateProblemsGroup();
                db.close();
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                sharedPreferences.edit().putInt("lessonProgress",+1).apply();
            } catch (SQLiteException e) {
                Toast toast2 = Toast.makeText(this,
                        "Database unavailable3",
                        Toast.LENGTH_SHORT);
                toast2.show();
            }


        }
    }

    public void updateProblemsGroup() {
        try {
            SQLiteOpenHelper lessonDatabaseHelper = new ProjectDatabaseHelper(this);
            db = lessonDatabaseHelper.getWritableDatabase();
            ContentValues groupValues = new ContentValues();
            groupValues.put(Contract.ProblemSchedule.COLUMN_GROUP, 1);
            db.update(Contract.ProblemSchedule.TABLE_NAME, groupValues,
                    Contract.ProblemSchedule.COLUMN_LESSON_NUMBER + "= ?",
                    new String[]{Integer.toString(lessonChoice)});
            db.close();
        } catch (SQLiteException e) {
            Toast toast2 = Toast.makeText(this,
                    "Database unavailable2",
                    Toast.LENGTH_SHORT);
            toast2.show();
        }
    }

    public void getLessonPass() {

        try {
            SQLiteOpenHelper lessonDatabaseHelper = new ProjectDatabaseHelper(this);
            db = lessonDatabaseHelper.getReadableDatabase();
            Cursor cursor = db.query(Contract.LessonTable.TABLE_NAME, new String[]
                    {Contract.LessonTable.COLUMN_PASS},Contract.LessonTable._ID + "=?",
                    new String[] {Integer.toString(lessonChoice)}, null, null, null);
            if (cursor.moveToFirst()) {
                lessonPass = cursor.getInt(0);
            }
            cursor.close();
            db.close();
        }catch (SQLiteException e) {
            Toast toast2 = Toast.makeText(this,
                    "Database unavailable1",
                    Toast.LENGTH_SHORT);
            toast2.show();
        }
    }



}
