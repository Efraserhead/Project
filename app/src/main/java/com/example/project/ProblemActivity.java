package com.example.project;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;

import android.database.sqlite.SQLiteDatabase;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class ProblemActivity extends AppCompatActivity {

    private TextView codeText;
    private TextView problem;
    private EditText answerText;
    private Button submitAnswer;
    private int problemCounter;
    private Date today;
    private Calendar calendar;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy", Locale.getDefault());

    private ArrayList<Problem> problemsSchedule = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem);
        calendar = Calendar.getInstance();
        today = calendar.getTime();
        answerText = findViewById(R.id.answerText);
        codeText = findViewById(R.id.codeText);
        problem = findViewById(R.id.problemText);
        submitAnswer = findViewById(R.id.submitAnswer);
        submitAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit();
            }
        });
        loadSchedule();

    }


    public void loadSchedule() {

        problemsSchedule.addAll(ProjectDatabase.getInstance(this).problemDao().getUnlockedProblems());
        for (Problem problem : problemsSchedule) {
            Date nextAvailable = problem.getNextAvailable();
            if (!(today.after(nextAvailable) || today.equals(nextAvailable))) {
                problemsSchedule.remove(problem);
            }
        }
        loadNextProblem();
    }



    public void submit() {
        int problemLevel = problemsSchedule.get(problemCounter).getLevel();
        String answer = answerText.getText().toString().toLowerCase().trim();
        String correctAnswer = problemsSchedule.get(problemCounter).getAnswer();
        if (TextUtils.isEmpty(answer)) {
            Toast toast = Toast.makeText(this,
                    "please enter your answer",
                    Toast.LENGTH_SHORT);
            toast.show();
        } else {
                if (answer.compareTo(correctAnswer) == 0) {
                    Toast toast2 = Toast.makeText(this,
                        "CORRECT!", Toast.LENGTH_SHORT);
                    toast2.show();
                    switch (problemLevel) {
                        case 1:
                            calendar.add(5, 3);
                        case 2:
                            calendar.add(5, 7);
                        case 3:
                            calendar.add(5, 14);
                        case 4:
                            calendar.add(5, 30);
                        case 5:
                            calendar.add(5, 30);
                    }
                    if (problemLevel < 5) {
                        problemsSchedule.get(problemCounter).setLevel(+1);
                    }
                }   else {
                Toast toast3 = Toast.makeText(this,
                        "Incorrect",
                        Toast.LENGTH_SHORT);
                toast3.show();
                    switch (problemLevel) {
                        case 1:
                            calendar.add(5, 1);
                        case 2:
                            calendar.add(5, 1);
                        case 3:
                            calendar.add(5, 3);
                        case 4:
                            calendar.add(5, 7);
                        case 5:
                            calendar.add(5, 14);
                    }
                if (problemLevel > 1) {
                    problemsSchedule.get(problemCounter).setLevel(-1);
                }
            }
                problemsSchedule.get(problemCounter).setNextAvailable(calendar.getTime());
                ProjectDatabase.getInstance(this).problemDao().update(problemsSchedule.get(problemCounter));
                calendar = Calendar.getInstance();
                problemCounter++;
                loadNextProblem();
        }


    }

    public void loadNextProblem() {

        if(problemCounter < problemsSchedule.size()) {
            codeText.setText(problemsSchedule.get(problemCounter).getProblemCode());
            problem.setText(problemsSchedule.get(problemCounter).getProblem());
        }
        else {
            Toast toast = Toast.makeText(this,
                    "You have cleared all of Today's problems",
                    Toast.LENGTH_SHORT);
            toast.show();
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);

        }
    }













}