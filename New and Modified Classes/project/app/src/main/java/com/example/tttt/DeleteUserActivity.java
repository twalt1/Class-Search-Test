package com.example.tttt;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class DeleteUserActivity extends AppCompatActivity {

    ImageButton btn1;
    Button btn2, btn3, btn4, btn5;
    EditText text1, text2;
    DBUser db1;
    DBInstructor db2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deleteuser);

        btn1 = findViewById(R.id.back_button_d);
        btn2 = findViewById(R.id.viewAllMembers);
        btn4 = findViewById(R.id.viewAllIns);
        btn3 = findViewById(R.id.deleteMembers);
        btn5 = findViewById(R.id.deleteIns);

        text1 = findViewById(R.id.getCusID);
        text2 = findViewById(R.id.getInsID);

        db1 = new DBUser(this);
        db2 = new DBInstructor(this);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                startActivity(intent);

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor res = db1.getAllData();
                if (res.getCount() == 0) {

                    showMessage("Error", "Nothing Found");
                    return;

                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {

                    buffer.append("ID :" + res.getString(0) + "\n");
                    buffer.append("Username :" + res.getString(1) + "\n");
                    buffer.append("Password :" + res.getString(2) + "\n");
                    buffer.append("Email :" + res.getString(3) + "\n");
                    buffer.append("Age :" + res.getString(4) + "\n");
                    buffer.append("Phone Number :" + res.getString(5) + "\n\n");

                }

                showMessage("Data", buffer.toString());

            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Integer deletedRows = db1.deleteData(text1.getText().toString());

                if (deletedRows > 0) {

                    Toast.makeText(DeleteUserActivity.this, "Data Updated.", Toast.LENGTH_LONG).show();

                }   else {

                    Toast.makeText(DeleteUserActivity.this,"Data not Updated", Toast.LENGTH_LONG).show();

                }

            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor res = db2.getAllData();
                if (res.getCount() == 0) {

                    showMessage("Error", "Nothing Found");
                    return;

                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {

                    buffer.append("ID :" + res.getString(0) + "\n");
                    buffer.append("Username :" + res.getString(1) + "\n");
                    buffer.append("Password :" + res.getString(2) + "\n");
                    buffer.append("Email :" + res.getString(3) + "\n");
                    buffer.append("Age :" + res.getString(4) + "\n");
                    buffer.append("Phone Number :" + res.getString(5) + "\n\n");

                }

                showMessage("Data", buffer.toString());

            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Integer deletedRows = db2.deleteData(text2.getText().toString());

                if (deletedRows > 0) {

                    Toast.makeText(DeleteUserActivity.this, "Data Updated.", Toast.LENGTH_LONG).show();

                }   else {

                    Toast.makeText(DeleteUserActivity.this,"Data not Updated", Toast.LENGTH_LONG).show();

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
