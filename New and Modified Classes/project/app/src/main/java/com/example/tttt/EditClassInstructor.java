package com.example.tttt;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class EditClassInstructor extends AppCompatActivity {

    Button viewClasses, cancelClasses, editClass;
    ImageButton back;
    DBClass db3;
    Spinner typeDropDown, difficultyDropDown, dayOfWeekDropDown;
    EditText getClassId, getClassTitle, getClassDescription, getClassTime;
    TextInputEditText capacity, startTime;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_class_instructor);

        //drop down list to select type of class
        typeDropDown = (Spinner) findViewById(R.id.classType_id2);
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.classType));
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeDropDown.setAdapter(typeAdapter);

        //drop down list for difficulty
        difficultyDropDown = (Spinner) findViewById(R.id.classDifficulty_id2);
        ArrayAdapter<String> difficultyAdapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.difficultyType));
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultyDropDown.setAdapter(difficultyAdapter);

        //drop down list for dayofweek
        dayOfWeekDropDown = (Spinner) findViewById(R.id.dayOfWeek_id3);
        ArrayAdapter<String> dayOfWeekAdapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.dayOfWeek));
        dayOfWeekAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dayOfWeekDropDown.setAdapter(dayOfWeekAdapter);

        capacity = findViewById(R.id.insEnterCap);
        viewClasses = findViewById(R.id.instructorView);
        back = findViewById(R.id.backBtn2);
        cancelClasses = findViewById(R.id.cancelClass);
        editClass = findViewById(R.id.confirmChangesInstructor);
        getClassId = findViewById(R.id.insGetClassID);
        getClassTitle = findViewById(R.id.insGetClassTitle);
        getClassDescription = findViewById(R.id.insGetClassDescr);
        getClassTime = findViewById(R.id.insGetTime);

        db3 = new DBClass(this);



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), InstructorActivity.class);
                startActivity(intent);
            }
        });

        cancelClasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean deletedRows = db3.deleteData(getClassId.getText().toString());

                if (deletedRows == true) {

                    Toast.makeText(EditClassInstructor.this, "Data Updated.", Toast.LENGTH_LONG).show();

                }   else {

                    Toast.makeText(EditClassInstructor.this,"Data not Updated", Toast.LENGTH_LONG).show();

                }

            }
        });

        viewClasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor res = db3.getAllData();
                if (res.getCount() == 0) {

                    showMessage("Error", "Nothing Found");
                    return;

                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {

                    buffer.append("ID : " + res.getString(0) + "\n");
                    buffer.append("Title : " + res.getString(1) + "\n");
                    buffer.append("Type : " + res.getString(2) + "\n");
                    buffer.append("Description : " + res.getString(3) + "\n");
                    buffer.append("Difficulty : " + res.getString(4) + "\n");
                    buffer.append("Capacity : " + res.getString(5) + "\n");
                    buffer.append("Date : " + res.getString(6) + "\n");
                    buffer.append("Time : " + res.getString(7) + "\n");
                    buffer.append("Instructor : " + res.getString(8) + "\n");
                    buffer.append("Day of week : " + res.getString(9) + "\n\n");

                }

                showMessage("Data", buffer.toString());

            }
        });



        editClass.setOnClickListener(new View.OnClickListener() {
            @Override
            //getClassId, getClassTitle
            public void onClick(View view) {
                try{
                    String classID = getClassId.getText().toString();
                    String type = typeDropDown.getSelectedItem().toString();
                    String diff = difficultyDropDown.getSelectedItem().toString();
                    String cap = capacity.getText().toString();
                    String time = getClassTime.getText().toString();
                    String classDescription = getClassDescription.getText().toString();
                    String classTitle = getClassTitle.getText().toString();
                    String dayOfWeek = dayOfWeekDropDown.getSelectedItem().toString();


                    if (classTitle.isEmpty() && classDescription.isEmpty()){
                        Toast.makeText(EditClassInstructor.this,"Both title and description fields cannot be empty", Toast.LENGTH_LONG).show();
                    }
                    else if(classID.isEmpty()){
                        Toast.makeText(EditClassInstructor.this,"Must input a class ID to update", Toast.LENGTH_LONG).show();
                    }
                    else if (!isInteger(cap)){
                        Toast.makeText(EditClassInstructor.this,"Please enter an integer for 'capacity'", Toast.LENGTH_LONG).show();
                    }
                    //if capacity is an integer but is smaller than 1
                    else if (isInteger(cap) && Integer.parseInt(cap) < 1){
                        Toast.makeText(EditClassInstructor.this, "Class's capacity must be > 1", Toast.LENGTH_SHORT).show();
                    }
                    //if description isn't empty and the class title isn't empty
                    else if(!classDescription.isEmpty() && !classTitle.isEmpty()){
                        db3.updateName(classID, classTitle);
                        db3.updateDescription(classID, classDescription);
                        db3.updateTime(classID, time);
                    }
                    else if(!classDescription.isEmpty()){
                        db3.updateDescription(classID, classDescription);
                    }
                    else{
                        db3.updateName(classID, classTitle);
                    }

                    db3.updateCapacity(classID, Integer.parseInt(cap));
                    db3.updateDifficulty(classID, diff);
                    db3.updateType(classID, type);
                    db3.updateDayOfWeek(classID, dayOfWeek);
                }
                catch(Exception e){
                    Toast.makeText(EditClassInstructor.this,"Exception occurred: " + e, Toast.LENGTH_LONG).show();
                }

                Toast.makeText(EditClassInstructor.this,"Updated Class!", Toast.LENGTH_LONG).show();

            }
        });
    }

    public void showMessage (String title, String Message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();

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

}