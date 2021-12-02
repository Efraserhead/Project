package com.example.project;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    Button problemButton;
    TextView username, lessonProgress;
    RecyclerView categoryView;
    List<Category> categories;
    CategoryViewModel categoryViewModel;
    LessonViewModel lessonViewModel;
    CategoryCardAdapter categoryCardAdapter;
    Button signUpButton;
    String userName;
    EditText makeUserName;
    Boolean signedUp;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finishAffinity();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        username = findViewById(R.id.username);
        lessonProgress = findViewById(R.id.progressText);
        categoryView = (RecyclerView) findViewById(R.id.category_recycler);
        categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        lessonViewModel = new ViewModelProvider(this).get(LessonViewModel.class);
        problemButton = findViewById(R.id.problemButton);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        signedUp = sharedPreferences.getBoolean("signedUp", false);
        signUpButton = (Button) findViewById(R.id.signUpButton);
        makeUserName = (EditText) findViewById(R.id.makeUsername);
        problemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enterProblems();
            }
        });
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUpButton();
            }
        });
        if (signedUp) {
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
            setSupportActionBar(toolbar);
            loadUserDetails();
            loadCategories();
            signUpButton.setVisibility(View.INVISIBLE);
            makeUserName.setVisibility(View.INVISIBLE);
        } else {
            problemButton.setVisibility(View.INVISIBLE);
            username.setVisibility(View.INVISIBLE);
            lessonProgress.setVisibility(View.INVISIBLE);
            categoryView.setVisibility(View.INVISIBLE);
        }

        //sets repeating alarm for daily Schedule notification
        setUpNotifications();


    }

    public void setUpNotifications() {
        Intent intent = new Intent(this, ScheduleReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 8, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 9);
        calendar.set(Calendar.MINUTE, 30);
        calendar.set(Calendar.SECOND, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

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
            populateDb();
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
            sharedPreferences.edit().putBoolean("signedUp", true).apply();
            sharedPreferences.edit().putString("username", userName).apply();

        }
    }


    public void loadUserDetails() {
        //loads progress related sharedPref values
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String userNameText = sharedPreferences.getString("username", "username");
        int lessonProgressNo = sharedPreferences.getInt("lessonProgress", 0);
        lessonProgress.setText("Lessons completed: " + lessonProgressNo);
        username.setText(userNameText);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the app bar.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //assigns actions to menu items
        switch (item.getItemId()) {
            case R.id.home:
                return true;
            case R.id.settingsTab:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            case R.id.exitTab:
                this.finishAffinity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void enterProblems() {
        //enter problem code activity
        Intent intent = new Intent(this, ProblemActivity.class);
        startActivity(intent);
    }

    public void loadCategories() {
        new LoadCategoriesAsyncTask(this).execute();
    }


    private static class LoadCategoriesAsyncTask extends AsyncTask<Void, Void, List<Category>> {
        private final WeakReference<HomeActivity> homeActivityWeakReference;

        public LoadCategoriesAsyncTask(HomeActivity activity) {
            homeActivityWeakReference = new WeakReference<HomeActivity>(activity);
        }

        @Override
        protected List<Category> doInBackground(Void... voids) {
            //load category db objects and map to a List of Category objects
            HomeActivity activity = homeActivityWeakReference.get();
            if (activity == null || activity.isFinishing()) {
                return null;
            }
            return activity.categoryViewModel.getCategories();
        }

        /**
         * @param categories
         */
        @Override
        protected void onPostExecute(List<Category> categories) {
            //assign Category List to recyclerView
            super.onPostExecute(categories);
            HomeActivity activity = homeActivityWeakReference.get();
            if (activity == null || activity.isFinishing()) {
                return;
            }
            activity.categories = categories;
            activity.categoryCardAdapter = new CategoryCardAdapter(categories);
            activity.categoryView.setAdapter(activity.categoryCardAdapter);
            activity.categoryView.setLayoutManager(new LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false));

        }
    }

    public void populateDb() {
        new PrepopulateDataBaseAsyncTask(this).execute();
    }


    private static class PrepopulateDataBaseAsyncTask extends AsyncTask<Void, Void, Void> {
        private final WeakReference<HomeActivity> mainActivityWeakReference;
        private final Date today;

        public PrepopulateDataBaseAsyncTask(HomeActivity activity) {
            today = Calendar.getInstance().getTime();
            mainActivityWeakReference = new WeakReference<HomeActivity>(activity);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            HomeActivity activity = mainActivityWeakReference.get();
            if (activity == null || activity.isFinishing()) {
                return null;
            }
            activity.categoryViewModel.insert(new Category("INTRODUCTION", "getting started", "", "", "", 1));
            activity.categoryViewModel.insert(new Category("PART I", "Variables", "Data types", "Operators", "Strings", 0));
            activity.categoryViewModel.insert(new Category("PART II", "Control structures", "Looping", "functions", "", 0));
            activity.categoryViewModel.insert(new Category("PART III", "OOP", "Data Structures", "Looping through structures", "", 0));
            activity.categoryViewModel.insert(new Category("PART IV", "Syntax", "Debugging", "", "", 0));
            activity.lessonViewModel.insert(new Lesson("getting started", 1, 0, Lesson.introduction, Lesson.variablesResource));
            activity.lessonViewModel.insert(new Lesson("variables", 2, 0, Lesson.variables, Lesson.variablesResource));
            activity.lessonViewModel.insert(new Lesson("data types", 2, 0, Lesson.dataTypes, Lesson.variablesResource));
            activity.lessonViewModel.insert(new Lesson("operators", 2, 0, Lesson.operators, Lesson.variablesResource));
            activity.lessonViewModel.insert(new Lesson("strings", 2, 0, Lesson.stringsPages, Lesson.variablesResource));
            activity.lessonViewModel.insert(new Lesson("control structures", 3, 0, Lesson.controlStructures, Lesson.variablesResource));
            activity.lessonViewModel.insert(new Lesson("looping ", 3, 0, Lesson.looping, Lesson.variablesResource));
            activity.lessonViewModel.insert(new Lesson("functions", 3, 0, Lesson.functions, Lesson.variablesResource));
            activity.lessonViewModel.insert(new Lesson("OOP", 4, 0, Lesson.OOP, Lesson.variablesResource));
            activity.lessonViewModel.insert(new Lesson("OOP II", 4, 0, Lesson.OOP2, Lesson.variablesResource));
            activity.lessonViewModel.insert(new Lesson("Data structures", 4, 0, Lesson.dataStructures, Lesson.variablesResource));
            activity.lessonViewModel.insert(new Lesson("data structures II", 4, 0, Lesson.dataStructuresLoop, Lesson.variablesResource));
            activity.lessonViewModel.insert(new Lesson("syntax", 5, 0, Lesson.syntax, Lesson.variablesResource));
            activity.lessonViewModel.insert(new Lesson("debugging", 5, 0, Lesson.debugging, Lesson.variablesResource));


            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            HomeActivity activity = mainActivityWeakReference.get();
            if (activity == null || activity.isFinishing()) {
                return;
            }
            Intent intent = new Intent(activity, LessonActivity.class);
            intent.putExtra("lessonChoice", 1);
            activity.startActivity(intent);
        }
    }
}
