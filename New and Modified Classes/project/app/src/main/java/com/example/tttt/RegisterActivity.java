package com.example.tttt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    DBUser db1;
    DBInstructor db2;
    TextInputEditText username, password, email, age, phonenumber;
    Button registerButton, loginButton;
    ImageButton backButton;
    CheckBox checkButton;

    //cannot be older than max age
    public static final int MAX_AGE = 130;
    public static final int MIN_AGE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //gym member
        db1 = new DBUser(this);
        //instructor
        db2 = new DBInstructor(this);
        username = findViewById(R.id.enterUsername_r);
        password = findViewById(R.id.enterPassword_r);
        email = findViewById(R.id.enterEmail_r);
        age = findViewById(R.id.enterAge_r);
        phonenumber = findViewById(R.id.enterPhoneNum_r);
        registerButton = findViewById(R.id.register_r);
        loginButton = findViewById(R.id.login_r);
        backButton = findViewById(R.id.back_button_r);
        checkButton = findViewById(R.id.check);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    AddData();
                } catch (Exception e){
                    Toast.makeText(RegisterActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                }

            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);

            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        });

    }

    public boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        }   catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    public boolean validatePhone(String phone){
        String phoneRegex = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$";
        Pattern phonePattern = Pattern.compile(phoneRegex);
        Matcher matcher = phonePattern.matcher(phone);

        return (matcher.find() && matcher.group().equals(phone));
    }


    public String getLoginSuccessString(boolean status){
        if (status){
            return "SUCCESSFULLY CREATED ACCOUNT!";
        } else {
            return "FAILED TO CREATE ACCOUNT!";
        }
    }

    public void AddData(){
        String usernameInput = username.getText().toString();
        String passwordInput = password.getText().toString();
        String emailInput = email.getText().toString();
        String ageInput = age.getText().toString();
        String phoneInput = phonenumber.getText().toString();
        boolean checkInput = checkButton.isChecked();

        boolean allValidInput = false;

        //INPUT VALIDATION
        //if empty string
        if (usernameInput.isEmpty() || passwordInput.isEmpty() || emailInput.isEmpty() || ageInput.isEmpty() || phoneInput.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Error: please fill out all fields.", Toast.LENGTH_SHORT).show();
        }
        // if age is not an integer
        else if (!isInteger(ageInput)){
            Toast.makeText(RegisterActivity.this, "Error: age has to be an integer.", Toast.LENGTH_SHORT).show();
        }
        // if age is not valid (between min age and max age)
        else if (Integer.parseInt(ageInput) <= MIN_AGE || Integer.parseInt(ageInput) >= MAX_AGE){
            Toast.makeText(RegisterActivity.this, "Error: age has to be between " + MIN_AGE + " and " + MAX_AGE, Toast.LENGTH_SHORT).show();
        }
        // if email not valid (no @ or period)
        else if(!emailInput.contains("@") || !emailInput.contains(".")) {
            Toast.makeText(RegisterActivity.this, "Error: email is invalid.", Toast.LENGTH_SHORT).show();
        }
        // if phone number not valid
        else if(!validatePhone(phoneInput)){
            Toast.makeText(RegisterActivity.this, "Error: phone number must contain only integers and greater than 9 characters", Toast.LENGTH_SHORT).show();
        }
        else {
            allValidInput = true;
        }

        //we will print out message to make sure we got the right input
        String enteredPrint = "You entered: ";
        enteredPrint += "\nusername = " + usernameInput;
        enteredPrint += "\npassword = " + passwordInput;
        enteredPrint += "\nemail = " + emailInput;
        enteredPrint += "\nage = " + ageInput;
        enteredPrint += "\nphone = " + phoneInput;
        enteredPrint += "\ncheckbox = " + checkInput;
        enteredPrint += "\nallValidInput = " + allValidInput;


        //if input is valid, then insert into database.
        if (allValidInput){
            Toast.makeText(RegisterActivity.this, enteredPrint, Toast.LENGTH_SHORT).show();

            //if instructor checked
            if (checkInput){
                //create an instructor
                Instructor newInstructor = new Instructor(usernameInput, passwordInput, emailInput, ageInput, phoneInput);

                //if user already existed
                if (db2.checkusername(newInstructor.getUserName())){
                    Toast.makeText(RegisterActivity.this, "Error: instructor already exists in database!", Toast.LENGTH_SHORT).show();
                }

                //if user does not exist, then add into database
                else {
                    Toast.makeText(RegisterActivity.this, "Creating an instructor account...", Toast.LENGTH_SHORT).show();
                    boolean insertResult = db2.insertData(newInstructor);
                    Toast.makeText(RegisterActivity.this, getLoginSuccessString(insertResult), Toast.LENGTH_SHORT).show();
                    //Go to login page
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }

            }


            //if instructor button not checked
            else {
                //create a gym member
                GymMember newGymMember = new GymMember(usernameInput, passwordInput, emailInput, ageInput, phoneInput);

                //if user already existed
                if (db1.checkusername(newGymMember.getUserName())){
                    Toast.makeText(RegisterActivity.this, "Error: gym member already exists in database!", Toast.LENGTH_SHORT).show();
                }

                //if user does not exist, then add into database
                else {
                    Toast.makeText(RegisterActivity.this, "Creating a gym member account...", Toast.LENGTH_SHORT).show();
                    boolean insertResult = db1.insertData(newGymMember);
                    Toast.makeText(RegisterActivity.this, getLoginSuccessString(insertResult), Toast.LENGTH_SHORT).show();
                    //Go to login page
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }

            }



        }


    }
    /*
    public void AddData() {

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String pass = password.getText().toString();
                String phoneNum = phonenumber.getText().toString();
                String a = age.getText().toString();
                String em = email.getText().toString();
                Integer a2 = 0;
                Boolean checked = false;

                if (check.isChecked()) {

                    checked = true;

                }   else {

                    checked = false;

                }

                if (!isInteger(a)) {

                    Toast.makeText(RegisterActivity.this, "Please enter correct number.", Toast.LENGTH_SHORT).show();

                }   else {

                    a2 = Integer.parseInt(a);

                }

                if (!checked) {

                    if (user.equals("") || pass.equals("") || phoneNum.equals("") || a.equals("") || em.equals("")) {

                        Toast.makeText(RegisterActivity.this, "Please enter all the fields.", Toast.LENGTH_SHORT).show();

                    } else {

                        Boolean checkuser = db1.checkusername(user);

                        if (!checkuser) {

                            Boolean insert = db1.insertData(user, pass, a, em, phoneNum);

                            if (insert) {

                                Toast.makeText(RegisterActivity.this, "Registered successfully!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                               // startActivity(intent);

                            } else {

                                Toast.makeText(RegisterActivity.this, "Registered failed.", Toast.LENGTH_SHORT).show();

                            }

                        } else {

                            Toast.makeText(RegisterActivity.this, "User already exists! Please login!", Toast.LENGTH_SHORT).show();

                        }

                    }

                }   else {

                    if (user.equals("") || pass.equals("") || phoneNum.equals("") || a.equals("") || em.equals("")) {

                        Toast.makeText(RegisterActivity.this, "Please enter all the fields.", Toast.LENGTH_SHORT).show();

                    } else {

                        Boolean checkuser = db2.checkusername(user);

                        if (!checkuser) {

                            Boolean insert = db2.insertData(user, pass, a, em, phoneNum);

                            if (insert) {

                                Toast.makeText(RegisterActivity.this, "Registered successfully!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                //startActivity(intent);

                            } else {

                                Toast.makeText(RegisterActivity.this, "Registered failed.", Toast.LENGTH_SHORT).show();

                            }

                        } else {

                            Toast.makeText(RegisterActivity.this, "User already exists! Please login!", Toast.LENGTH_SHORT).show();

                        }

                    }

                }

            }
        });


    }
    */
}