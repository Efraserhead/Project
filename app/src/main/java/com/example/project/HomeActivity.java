package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.Menu;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    Button reviewButton;
    TextView username, lessonProgress;
    RecyclerView categoryView;
    List<Category> categories;
    CategoryViewModel categoryViewModel;
    CategoryCardAdapter categoryCardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        username = findViewById(R.id.username);
        lessonProgress = findViewById(R.id.progressText);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        categoryView = (RecyclerView) findViewById(R.id.category_recycler);
        categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        reviewButton = findViewById(R.id.problemButton);

        //sets repeating alarm for daily Schedule notification
        Intent intent = new Intent(this, ScheduleReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 8, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 9);
        calendar.set(Calendar.MINUTE,30);
        calendar.set(Calendar.SECOND, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);

        loadUserDetails();
        reviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enterProblems();
            }
        });
        new LoadCategoriesAsyncTask(this).execute();
    }

    @Override
    public void onBackPressed() {
        //Blocks access to MainActivity after user has signed up
        super.onBackPressed();
        this.finishAffinity();
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
}
