package com.example.project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    Button signUpButton;
    String userName;
    EditText makeUserName;
    Boolean signedUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signUpButton = (Button) findViewById(R.id.signUpButton);
        makeUserName = (EditText) findViewById(R.id.makeUsername);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {signUpButton(); }
        });

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        signedUp = sharedPreferences.getBoolean("signedUp",false);
        if(signedUp) {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);

        }

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

            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
            sharedPreferences.edit().putBoolean("signedUp", true).apply();
            sharedPreferences.edit().putString("username",userName).apply();
            Intent intent = new Intent(this, LessonActivity.class);
            intent.putExtra("lessonChoice",1);
            startActivity(intent);
        }
    }

}


