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
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;


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
    private QuestionViewModel questionViewModel;
    private LessonViewModel lessonViewModel;


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
        lessonChoice = intent.getIntExtra("lessonChoice", 1);
        lessonPass = intent.getIntExtra("lesson pass", 0);
        questionViewModel = new ViewModelProvider(this).get(QuestionViewModel.class);
        lessonViewModel = new ViewModelProvider(this).get(LessonViewModel.class);
        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitAnswer();
            }
        });

        new LoadTestQuestionsAsyncTask(this).execute();

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
            new AlertDialog.Builder(this)
                    .setTitle("Nothing to submit")
                    .setMessage("please click on your chosen answer")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .create().show();

        }
        RadioButton selectedButton = findViewById(radioAnswers.getCheckedRadioButtonId());
        int correctAnswerId = questions.get(testCounter).getCorrectAnswerId();
        int selectedAnswerId = radioAnswers.indexOfChild(selectedButton);
        if (selectedAnswerId == correctAnswerId) {
            score++;
            testCounter++;
            nextQuestion();
            new AlertDialog.Builder(this)
                    .setTitle("Correct")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .create().show();
        } else {
            testCounter++;
            nextQuestion();
            new AlertDialog.Builder(this)
                    .setTitle("Incorrect")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .create().show();
        }

    }

    public void endTest() {
        //update database and pass lesson
        if (score >= 3) {
            updateLesson();
            new AlertDialog.Builder(this)
                    .setTitle("Lesson Passed!")
                    .setMessage("your score is " + score + " you have passed the Lesson")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .create().show();

        } else {
            new AlertDialog.Builder(this)
                    .setTitle("Lesson Failed")
                    .setMessage("your score is " + score + " you need to review the Lesson")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .create().show();
        }

        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }


    private static class LoadTestQuestionsAsyncTask extends AsyncTask<Void, Void, List<Question>> {
        private final WeakReference<TestActivity> testActivityWeakReference;

        public LoadTestQuestionsAsyncTask(TestActivity activity) {
            testActivityWeakReference = new WeakReference<TestActivity>(activity);
        }

        @Override
        protected List<Question> doInBackground(Void... voids) {
            TestActivity activity = testActivityWeakReference.get();
            if (activity == null || activity.isFinishing()) {
                return null;
            }
            return activity.questionViewModel.getLessonQuestions(activity.lessonChoice);
        }

        @Override
        protected void onPostExecute(List<Question> questions) {
            super.onPostExecute(questions);
            TestActivity activity = testActivityWeakReference.get();
            if (activity == null || activity.isFinishing()) {
                return;
            }
            activity.questions.addAll(questions);
            activity.nextQuestion();

        }
    }

    public void updateLesson() {
        if (lessonPass == 0) {
            //updateProblemsGroup();
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
            sharedPreferences.edit().putInt("lessonProgress", +1).apply();
            new UpdateLessonAsyncTask(this).execute();
        }


    }

    private static class UpdateLessonAsyncTask extends AsyncTask<Void, Void, Void> {

        private final WeakReference<TestActivity> testActivityWeakReference;

        public UpdateLessonAsyncTask(TestActivity activity) {
            testActivityWeakReference = new WeakReference<TestActivity>(activity);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            TestActivity activity = testActivityWeakReference.get();
            if (activity == null || activity.isFinishing()) {
                return null;
            }
            Lesson thisLesson = activity.lessonViewModel.getLesson(activity.lessonChoice);
            thisLesson.setPass(1);
            activity.lessonViewModel.update(thisLesson);
            return null;
        }
    }


    public void updateProblemsGroup() {

        problems.addAll(ProjectDatabase.getInstance(this).problemDao().getLessonProblems(lessonChoice));
        for (Problem problem : problems) {
            problem.setLevel(1);
            ProjectDatabase.getInstance(this).problemDao().insert(problem);
        }
    }


}
