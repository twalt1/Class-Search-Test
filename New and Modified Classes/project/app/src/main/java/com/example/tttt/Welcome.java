package com.example.tttt;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Welcome extends AppCompatActivity {

    TextView display;
    public static final int SPLASH_SCREEN = 2000;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        display = findViewById(R.id.welcome);

        Intent intent = getIntent();

        String got[] = intent.getStringExtra("name").split(" ");

        display.setText("Welcome\n" + got[0] + "!\n\n You are logged in as\n\n" +got[1]);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (got[1].equals("GymMember")) {

                    Intent intent = new Intent(getApplicationContext(), UserWelcome.class);
                    startActivity(intent);
                    finish();

                }   else if (got[1].equals("Instructor")) {

                    Intent intent = new Intent(getApplicationContext(), InstructorActivity.class);
                    startActivity(intent);
                    finish();

                }   else if (got[1].equals("Admin")) {

                    Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                    startActivity(intent);
                    finish();

                }

            }
        } ,SPLASH_SCREEN);

    }

}
