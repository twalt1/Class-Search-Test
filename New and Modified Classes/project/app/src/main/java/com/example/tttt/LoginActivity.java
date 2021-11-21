package com.example.tttt;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    ImageButton btn1;
    Button btn2, btn3;
    TextInputEditText input1, input2;
    DBUser db1;
    DBInstructor db2;

    //when someone logs in, we'll keep their session here
    SharedPreferences currentUserSession;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db1 = new DBUser(this);
        db2 = new DBInstructor(this);

        btn1 = findViewById(R.id.back_button_l);
        btn3 = findViewById(R.id.login_l);
        btn2 = findViewById(R.id.register_l);
        input1 = findViewById(R.id.enterUsername_l);
        input2 = findViewById(R.id.enterPassword_l);

        //instantiate session
        currentUserSession = getSharedPreferences("currentUserSession", Context.MODE_PRIVATE);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {

                Intent i=new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);

            }

        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {

                SharedPreferences.Editor editor = currentUserSession.edit();

                try{
                    String enteredUsername = input1.getText().toString();
                    String enteredPassword = input2.getText().toString();

                    //if this user is Admin
                    if (enteredUsername.equals("admin") && (enteredPassword.equals("admin123"))) {

                        String aString = enteredUsername + " Admin";

                        Intent intent = new Intent(getApplicationContext(), Welcome.class);
                        intent.putExtra("name", aString);
                        startActivity(intent);
                        return;
                    }
                    //check if exists in User (Gym Member) database
                    Boolean flag1 = db1.checkusernamepassword(enteredUsername, enteredPassword);
                    //check if exists in Instructor database
                    Boolean flag2 = db2.checkusernamepassword(enteredUsername, enteredPassword);

                    //if not in Gym member database
                    if (!flag1){
                        //but in instructor database
                        if (flag2) {

                            //we'll store instructor username and email in the Shared Preferences session
                            Instructor instructor = db2.getInstructorFromUsernameAndPassword(enteredUsername, enteredPassword);
                            editor.putString("username", instructor.getUserName());
                            editor.putString("email", instructor.getEmail());
                            editor.commit();

                            //let the user know login is successful
                            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

                            String aString = enteredUsername + " Instructor";

                            Intent intent = new Intent(getApplicationContext(), Welcome.class);
                            intent.putExtra("name", aString);
                            startActivity(intent);

                        }
                        //means not in gym member database nor in instructor database
                        else {

                            Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();

                        }
                    }
                    //if in Gym Member database
                    else {

                        Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                        //start new welcome page

                        String aString = enteredUsername + " GymMember";

                        Intent intent = new Intent(getApplicationContext(), Welcome.class);
                        intent.putExtra("name", aString);
                        startActivity(intent);

                    }
                }
                catch(Exception e){
                    Toast.makeText(LoginActivity.this, "ERROR: " + e.toString(), Toast.LENGTH_SHORT).show();

                }
            }

        });

    }

}
