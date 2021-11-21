package com.example.tttt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class InstructorActivity extends AppCompatActivity {


    Button btn1, btn2, btn3, btn4;
    ImageButton btn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor);


        btn1 = findViewById(R.id.instructorEdit);
        btn2 = findViewById(R.id.instructorAdd);
        btn = findViewById(R.id.instructorBack);
        btn3 = findViewById((R.id.searchInstructor));

        btn1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), EditClassInstructor.class);
                startActivity(intent);

            }

        });

        btn3.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), SearchInstructorName.class);
                startActivity(intent);

            }

        });

        btn2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), CreateClassInstructor.class);
                startActivity(intent);

            }

        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        });

    }


}