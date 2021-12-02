package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class AdditionalResourcesActivity extends AppCompatActivity {
    int resourcesChoice;
    ListView resources;
    LessonViewModel lessonViewModel;
    ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additional_resources);
        resources = findViewById(R.id.resourcesList);
        Intent intent = getIntent();
        resourcesChoice = intent.getIntExtra("resourcesChoice", 0);
        lessonViewModel = new ViewModelProvider(this).get(LessonViewModel.class);
        new LoadLinksAsyncTask(this).execute();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> listView,
                                    View itemView,
                                    int position,
                                    long id) {
                String link = adapter.getItem(position).toString();
                Uri uri = Uri.parse(link);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        };
        resources.setOnItemClickListener(itemClickListener);

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

    private static class LoadLinksAsyncTask extends AsyncTask<Void,Void,ArrayList<String>> {
        private final WeakReference<AdditionalResourcesActivity> additionalResourcesActivityWeakReference;


        public LoadLinksAsyncTask(AdditionalResourcesActivity activity) {
            additionalResourcesActivityWeakReference = new WeakReference<AdditionalResourcesActivity>(activity);
        }

        @Override
        protected ArrayList<String> doInBackground(Void... voids) {
            AdditionalResourcesActivity activity = additionalResourcesActivityWeakReference.get();
            if (activity == null || activity.isFinishing()) {
                return null;
            }
            return activity.lessonViewModel.getLesson(activity.resourcesChoice).getResourceLinks();
        }

        @Override
        protected void onPostExecute(ArrayList<String> strings) {
            super.onPostExecute(strings);
            AdditionalResourcesActivity activity = additionalResourcesActivityWeakReference.get();
            if (activity == null || activity.isFinishing()) {
                return;
            }
            activity.adapter = new ArrayAdapter<>(activity,android.R.layout.simple_list_item_1,strings);
            activity.resources.setAdapter(activity.adapter);
        }
    }
}