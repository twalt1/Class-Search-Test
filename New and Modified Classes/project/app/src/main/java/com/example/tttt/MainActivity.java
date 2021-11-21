package com.example.tttt;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.login_m);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);

            }
        });

        btn2 = findViewById(R.id.register_m);

        btn2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                Intent i=new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);

            }


        });


    }

}

