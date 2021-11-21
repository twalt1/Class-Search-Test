package com.example.tttt;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ClassSearchActivity extends AppCompatActivity {
    DBClass db = new DBClass(this);
    EditText txt;
    Button btn;
    String text;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_class_search);

        btn = findViewById(R.id.searchInst);
        txt = findViewById(R.id.txtInstructor);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = txt.getText().toString();
                Cursor cursor = db.getAllData();
                cursor.moveToNext();
                if (cursor.getCount() == 0) {
                    showMessage("Error", "No Instructors Registered.");
                } else {
                    StringBuffer buffer = new StringBuffer();

                    if (text.equals(cursor.getString(8))) {
                        buffer.append("Title :" + cursor.getString(1) + "\n");
                        buffer.append(" Type:" + cursor.getString(2) + "\n");
                        buffer.append("Description :" + cursor.getString(3) + "\n");
                        buffer.append("Difficulty :" + cursor.getString(4) + "\n");
                        buffer.append("Capacity :" + cursor.getString(5) + "\n");
                        buffer.append("Date :" + cursor.getString(6) + "\n");
                        buffer.append("Time :" + cursor.getString(7)+"\n");
                        buffer.append("Instructor Email :" + cursor.getString(8)+"\n");
                        buffer.append("Day of Week :" + cursor.getString(9)+"\n\n");
                    };
                    while (cursor.moveToNext()) {
                        if (text.equals(cursor.getString(8))) {
                            buffer.append("Title :" + cursor.getString(1) + "\n");
                            buffer.append(" Type:" + cursor.getString(2) + "\n");
                            buffer.append("Description :" + cursor.getString(3) + "\n");
                            buffer.append("Difficulty :" + cursor.getString(4) + "\n");
                            buffer.append("Capacity :" + cursor.getString(5) + "\n");
                            buffer.append("Date :" + cursor.getString(6) + "\n");
                            buffer.append("Time :" + cursor.getString(7)+"\n");
                            buffer.append("Instructor Email :" + cursor.getString(8)+"\n");
                            buffer.append("Day of Week :" + cursor.getString(9)+"\n\n");
                        }

                    }
                    if (buffer.equals("")) {
                        showMessage("Error", "No instructors of email " + text + " " +
                                "were found.");
                    }
                    showMessage("Instructors", buffer.toString());
                }
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
}
