package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;


import android.content.Intent;
import android.content.SharedPreferences;
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

    private int lessonChoice;
    private int testCounter;
    private int score;
    private int lessonPass;
    private TextView questionText;
    private RadioGroup radioAnswers;
    private RadioButton answer1;
    private RadioButton answer2;
    private RadioButton answer3;
    private Button submit;
    private final ArrayList<Question> questions = new ArrayList<>();
    private final ArrayList<Problem> problems = new ArrayList<>();
    private Lesson thisLesson;




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

        loadTestQuestions();
        getLessonPass();
        nextQuestion();

    }

    public void nextQuestion() {
        if (testCounter < questions.size()) {
            String thisQuestion = questions.get(testCounter).getQuestion();
            String thisAnswer1 = questions.get(testCounter).getAnswerId0();
            String thisAnswer2 = questions.get(testCounter).getAnswerId1();
            String thisAnswer3 = questions.get(testCounter).getAnswerId2();
            radioAnswers.clearCheck();
            questionText.setText(thisQuestion);
            answer1.setText(thisAnswer1);
            answer2.setText(thisAnswer2);
            answer3.setText(thisAnswer3);
        }
            radioAnswers.clearCheck();
            questionText.setText(R.string.hyperlink);
            questionText.setMovementMethod(LinkMovementMethod.getInstance());
            radioAnswers.setVisibility(View.INVISIBLE);
            submit.setText("FINISH");

    }

    public void submitAnswer() {

         if (radioAnswers.getCheckedRadioButtonId() == -1) {
             if (testCounter >= questions.size()) {
                 endTest();
             }
                 Toast toast = Toast.makeText(this,
                         "select one answer",
                         Toast.LENGTH_SHORT);
                 toast.show();

         }
        RadioButton selectedButton = findViewById(radioAnswers.getCheckedRadioButtonId());
        int correctAnswerId = questions.get(testCounter).getCorrectAnswerId();
        int selectedAnswerId = radioAnswers.indexOfChild(selectedButton);
         if(selectedAnswerId == correctAnswerId) {
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

    public void loadTestQuestions() {

        questions.addAll(ProjectDatabase.getInstance(this).questionDao().getLessonQuestions(lessonChoice));


    }

    public void updateLesson() {
        if (lessonPass == 0) {
            thisLesson.setPass(1);
            ProjectDatabase.getInstance(this).lessonDao().update(thisLesson);
            //updateProblemsGroup();
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
            sharedPreferences.edit().putInt("lessonProgress",+1).apply();
        }
    }

    public void updateProblemsGroup() {

            problems.addAll(ProjectDatabase.getInstance(this).problemDao().getLessonProblems(0,lessonChoice));
            for(Problem problem: problems) {
                problem.setLevel(1);
                ProjectDatabase.getInstance(this).problemDao().insert(problem);
            }
    }

    public void getLessonPass() {
        thisLesson = ProjectDatabase.getInstance(this).lessonDao().getLesson(lessonChoice);
        lessonPass = thisLesson.getPass();
    }



}
