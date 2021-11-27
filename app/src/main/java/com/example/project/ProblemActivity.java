package com.example.project;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;


import android.content.Intent;


import android.icu.text.SimpleDateFormat;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class ProblemActivity extends AppCompatActivity {

    private ImageView codePic;
    private TextView problemQuestion;
    private EditText answerText;
    private Button submitAnswer;
    private int problemCounter;
    private Date today;
    private Calendar calendar;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy", Locale.getDefault());
    private ProblemViewModel problemViewModel;

    private ArrayList<Problem> problemsSchedule = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        calendar = Calendar.getInstance();
        today = calendar.getTime();
        answerText = findViewById(R.id.answerText);
        codePic = (ImageView) findViewById(R.id.codePic);
        problemQuestion = findViewById(R.id.problemText);
        submitAnswer = findViewById(R.id.submitAnswer);
        submitAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit();
            }
        });
        problemViewModel = new ViewModelProvider(this).get(ProblemViewModel.class);
        new LoadProblemScheduleAsyncTask(this).execute();
        loadNextProblem();

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

    private static class LoadProblemScheduleAsyncTask extends AsyncTask<Void, Void, List<Problem>> {
        private WeakReference<ProblemActivity> problemActivityWeakReference;

        public LoadProblemScheduleAsyncTask(ProblemActivity activity) {
            problemActivityWeakReference = new WeakReference<ProblemActivity>(activity);
        }

        @Override
        protected List<Problem> doInBackground(Void... voids) {
            ProblemActivity activity = problemActivityWeakReference.get();
            if (activity == null || activity.isFinishing()) {
                return null;
            }
            return activity.problemViewModel.getUnlockedProblems();
        }

        @Override
        protected void onPostExecute(List<Problem> problems) {
            super.onPostExecute(problems);
            ProblemActivity activity = problemActivityWeakReference.get();
            if (activity == null || activity.isFinishing()) {
                return;
            }
            for (Problem problem : problems) {
                if (activity.today.after(problem.getNextAvailable()) || activity.today.equals(problem.getNextAvailable())) {
                    activity.problemsSchedule.add(problem);
                }
            }
        }
    }


    public void submit() {
        calendar = Calendar.getInstance();
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
            } else {
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
            new UpdateProblemAsyncTask(this).execute();
            problemCounter++;
            loadNextProblem();
        }


    }

    private static class UpdateProblemAsyncTask extends AsyncTask<Void,Void,Void> {
        private WeakReference<ProblemActivity> problemActivityWeakReference;

        public UpdateProblemAsyncTask(ProblemActivity activity) {
            problemActivityWeakReference = new WeakReference<ProblemActivity>(activity);
        }


        @Override
        protected Void doInBackground(Void... voids) {
            ProblemActivity activity = problemActivityWeakReference.get();
            if (activity == null || activity.isFinishing()) {
                return null;
            }
            activity.problemViewModel.update(activity.problemsSchedule.get(activity.problemCounter));
            return null;
        }
    }


    public void loadNextProblem() {

        if (problemCounter < problemsSchedule.size()) {
            codePic.setImageResource(problemsSchedule.get(problemCounter).getProblemCode());
            problemQuestion.setText(problemsSchedule.get(problemCounter).getProblemQuestion());
        } else {
            Toast toast = Toast.makeText(this,
                    "You have cleared all of your scheduled problems",
                    Toast.LENGTH_SHORT);
            toast.show();
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);

        }
    }


}