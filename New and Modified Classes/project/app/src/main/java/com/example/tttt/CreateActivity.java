package com.example.tttt;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class CreateActivity extends MainActivity implements DatePickerDialog.OnDateSetListener{
    DBClass db3;
    TextInputEditText title, description, capacity, startTime;
    Spinner typeDropDown, difficultyDropDown, dayOfWeekDropDown;
    Button btn, btn1;
    ImageButton btn2;
    static String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        db3 = new DBClass(this);
        title = findViewById(R.id.insEnterTitleInstructor);
        //drop down list to select type of class
        typeDropDown = (Spinner) findViewById(R.id.classType_id);
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.classType));
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeDropDown.setAdapter(typeAdapter);

        //drop down list for difficulty
        difficultyDropDown = (Spinner) findViewById(R.id.classDifficulty_id);
        ArrayAdapter<String> difficultyAdapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.difficultyType));
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultyDropDown.setAdapter(difficultyAdapter);

        //drop down list for dayofweek
        dayOfWeekDropDown = (Spinner) findViewById(R.id.dayOfWeek_id2);
        ArrayAdapter<String> dayOfWeekAdapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.dayOfWeek));
        dayOfWeekAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dayOfWeekDropDown.setAdapter(dayOfWeekAdapter);


        description = findViewById(R.id.enterDesc);
        capacity = findViewById(R.id.insEnterCap);
        startTime = findViewById(R.id.insEnterTime);
        btn = findViewById(R.id.chooseDate);
        btn1 = findViewById(R.id.confirmChangesBtn);
        btn2 = findViewById(R.id.backbutton3);

        //AddData();

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                startActivity(intent);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                showPickerDialog();
            }
            private void showPickerDialog(){
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        CreateActivity.this,
                        CreateActivity.this,
                        Calendar.getInstance().get(Calendar.YEAR),
                        Calendar.getInstance().get(Calendar.MONTH),
                        Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String titl = title.getText().toString();
                String type = typeDropDown.getSelectedItem().toString();
                String desc = description.getText().toString();
                String diff = difficultyDropDown.getSelectedItem().toString();
                String cap = capacity.getText().toString();
                String time = startTime.getText().toString();
                String dayOfWeek = dayOfWeekDropDown.getSelectedItem().toString();

                //let's make sure all inputs are valid
                boolean allValidInput = false;

                //we will print out message to make sure we got the right input
                String enteredPrint = "You entered: ";
                enteredPrint += "\ntitle = " + titl;
                enteredPrint += "\ntype = " + type;
                enteredPrint += "\ndescription = " + desc;
                enteredPrint += "\ndifficulty = " + diff;
                enteredPrint += "\ncapacity = " + cap;
                enteredPrint += "\nday of week = " + dayOfWeek;
                enteredPrint += "\ntime = " + time;
                Toast.makeText(getApplicationContext(), enteredPrint, Toast.LENGTH_SHORT).show();

                //if capacity is not an integer
                if (!isInteger(cap)) {
                    Toast.makeText(getApplicationContext(), "Please enter an integer for 'capacity'", Toast.LENGTH_SHORT).show();
                }

                //if capacity is an integer but is smaller than 1
                else if (isInteger(cap) && Integer.parseInt(cap) < 1){
                    Toast.makeText(getApplicationContext(), "Class's capacity must be > 1", Toast.LENGTH_SHORT).show();
                }
                //if one of the inputs is empty
                else if (titl.equals("") || desc.equals("") || diff.equals("") || cap.equals("") || time.equals("") || date.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please enter all the fields.", Toast.LENGTH_SHORT).show();
                }
                else {
                    //if all the above cases are false, then the inputs are valid
                    allValidInput = true;
                }

                if (allValidInput) {


                    if (!db3.checkExist(type, date)) {

                        Toast.makeText(getApplicationContext(), "Class already exists.", Toast.LENGTH_SHORT).show();

                    }   else {

                        //Now we insert (create) a new class in the database
                        Boolean insert = db3.insertData(titl, type, desc, diff, Integer.parseInt(cap), date, time, "admin", dayOfWeek);

                        if (insert) {

                            //Integer res = db3.getNum(type, date);
                            //String t = String.valueOf(res);
                            //Toast.makeText(getApplicationContext(), t, Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(), "Class created!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), InstructorActivity.class);
                            startActivity(intent);

                        } else {

                            Toast.makeText(getApplicationContext(), "Class not created.", Toast.LENGTH_SHORT).show();

                        }
                    }
                }

                /*
                if (!isInteger(cap)) {

                    Toast.makeText(CreateActivity.this, "Please enter correct number.", Toast.LENGTH_SHORT).show();

                }   else {

                    c2 = Integer.parseInt(cap);

                }



                if (titl.equals("") || desc.equals("") || diff.equals("") || cap.equals("") || time.equals("") || date.equals("")) {

                    Toast.makeText(CreateActivity.this, "Please enter all the fields.", Toast.LENGTH_SHORT).show();

                } else {

                    Boolean insert = db3.insertData(titl, type, desc, diff, c2, date, time, "admin");

                    if (insert) {

                        Toast.makeText(CreateActivity.this, "Class created!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                        startActivity(intent);

                    } else {

                        Toast.makeText(CreateActivity.this, "Class not created.", Toast.LENGTH_SHORT).show();

                    }


                }
                */

            }
        });

    }

    public boolean isInteger(String s) {

        try {

            Integer.parseInt(s);

        }   catch (NumberFormatException e) {

            return false;

        }   catch (NullPointerException e) {

            return false;

        }

        return true;

    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        month += 1;
        date = "day/month/year: " + dayOfMonth + "/" + month + "/" + year;
    }


    //public void AddData() {

    //}


}
