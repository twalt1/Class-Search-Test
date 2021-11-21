package com.example.tttt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class AdminActivity extends AppCompatActivity {

    //UserAccount admin;
    Button btn1, btn2, btn3;
    ImageButton btn;

    //public void buildAdmin(){
    //    admin = new UserAccount("admin", "admin123", "admin@uottawa.ca", Integer.toString(20), "6131234567");
    //}

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        btn1 = findViewById(R.id.deleteUser);
        btn2 = findViewById(R.id.editClass);
        btn3 = findViewById(R.id.addClass);
        btn = findViewById(R.id.backToMain);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), DeleteUserActivity.class);
                startActivity(intent);

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {

             public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), ClassActivity.class);
                startActivity(intent);

            }

        });

        btn3.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), CreateActivity.class);
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
