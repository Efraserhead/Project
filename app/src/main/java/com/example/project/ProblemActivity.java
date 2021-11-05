package com.example.project;


import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class ProblemActivity extends AppCompatActivity {

    private SQLiteDatabase db;
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
        loadNextProblem();
    }


    public void loadSchedule() {
        try {
            SQLiteOpenHelper projectDatabaseHelper = new ProjectDatabaseHelper(this);
            db = projectDatabaseHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM " + Contract.ProblemSchedule.TABLE_NAME,null);
            if (cursor.moveToFirst()) {
                do {
                    Problem problem = new Problem(cursor.getInt(0), cursor.getString(1),
                            cursor.getString(2), cursor.getString(3),cursor.getInt(4),cursor.getString(5));
                    if(!(problem.getGroup()==0)) {
                        Date nextAvailable = sdf.parse(problem.getLastCompleted());
                        if(today.after(nextAvailable) || today.equals(nextAvailable)) {
                        problemsSchedule.add(problem);
                        }
                    }
                }
                while (cursor.moveToNext());
            }

        }catch (SQLiteException | ParseException e) {
            Toast toast2 = Toast.makeText(this,
                    "Database unavailable",

                    Toast.LENGTH_SHORT);
            toast2.show();
        }
    }

    public void submit() {
        int problemGroup = problemsSchedule.get(problemCounter).getGroup();
        int problemId = problemsSchedule.get(problemCounter).getId();
        String nextAvailable;
        String answer = answerText.getText().toString().toLowerCase().trim();
        String correctAnswer = problemsSchedule.get(problemCounter).getAnswer();
        if (TextUtils.isEmpty(answer)) {
            Toast toast = Toast.makeText(this,
                    "please enter your answer",
                    Toast.LENGTH_SHORT);
            toast.show();
        }
            else{

                if (answer.compareTo(correctAnswer) == 0) {
                    switch (problemGroup) {
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
                    Toast toast2 = Toast.makeText(this,
                                "CORRECT!",

                            Toast.LENGTH_SHORT);
                    toast2.show();
                    if (problemGroup < 5) {
                        problemGroup++;
                    }
              } else {
                  switch (problemGroup) {
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
                Toast toast3 = Toast.makeText(this,
                        "Incorrect",
                        Toast.LENGTH_SHORT);
                toast3.show();
                if (problemGroup > 1) {
                    problemGroup--;
                }
            }
            nextAvailable = sdf.format(calendar.getTime());
            updateProblem(nextAvailable, problemGroup, problemId);
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



    public void updateProblem(String nextAvailable, int problemGroup, int problemId) {
        try{
            SQLiteOpenHelper lessonDatabaseHelper = new ProjectDatabaseHelper(this);
            db = lessonDatabaseHelper.getWritableDatabase();
            ContentValues nextAvailableValues = new ContentValues();
            nextAvailableValues.put(Contract.ProblemSchedule.COLUMN_DATE_DONE,nextAvailable);
            ContentValues groupValues = new ContentValues();
            groupValues.put(Contract.ProblemSchedule.COLUMN_GROUP,problemGroup);
            db.update(Contract.ProblemSchedule.TABLE_NAME,groupValues,Contract.ProblemSchedule._ID + "=?",
                    new String[] {Integer.toString(problemId)});
            db.update(Contract.ProblemSchedule.TABLE_NAME,nextAvailableValues,Contract.ProblemSchedule._ID + "=?",
                    new String[] {Integer.toString(problemId)});
            db.close();
        }catch (SQLiteException e) {
            Toast toast2 = Toast.makeText(this,
                    "Database unavailable",

                    Toast.LENGTH_SHORT);
            toast2.show();
        }
    }








}