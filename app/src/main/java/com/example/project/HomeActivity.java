package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String userNameText = sharedPreferences.getString("username", "username");
        int lessonProgressNo = sharedPreferences.getInt("lessonProgress", 0);
        lessonProgress.setText("Lessons completed: " + lessonProgressNo);
        username.setText(userNameText);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        categoryView = (RecyclerView) findViewById(R.id.category_recycler);
        categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        reviewButton = findViewById(R.id.problemButton);
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
        super.onBackPressed();
        this.finishAffinity();
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

    public void enterProblems() {
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
            HomeActivity activity = homeActivityWeakReference.get();
            if (activity == null || activity.isFinishing()) {
                return null;
            }
            return activity.categoryViewModel.getCategories();
        }

        @Override
        protected void onPostExecute(List<Category> categories) {
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
