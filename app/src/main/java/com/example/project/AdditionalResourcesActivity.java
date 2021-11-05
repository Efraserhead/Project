package com.example.project;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class AdditionalResourcesActivity extends AppCompatActivity {

    ListView resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additional_resources);
        resources = findViewById(R.id.resourcesList);
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> listView,
                                    View itemView,
                                    int position,
                                    long id) {
                if (position == 0) {

                }
            }
        };

    }
}