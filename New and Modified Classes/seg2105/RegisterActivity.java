package com.example.seg2105;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class RegisterActivity extends MainActivity {

    ImageButton btn1;
    Button btn2, btn3;
    EditText enterEmail_R, enterPassword;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btn1 = findViewById(R.id.back_button_r);
        btn2 = findViewById(R.id.login_r);
        btn3 = findViewById(R.id.register_r);
        enterEmail_R = findViewById(R.id.enterEmail_R);
        enterPassword = findViewById(R.id.enterPassword);

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

                Intent i=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);

            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UserAccount userAccount;

                try{
                    userAccount = new UserAccount("John Doe", -1, enterEmail_R.getText().toString(), "613000000", "JohnDoe00", enterPassword.getText().toString(), "Member");
                    Toast.makeText(RegisterActivity.this, userAccount.toString(), Toast.LENGTH_SHORT).show();
                }
               catch (Exception e){
                   Toast.makeText(RegisterActivity.this, "Error creating account", Toast.LENGTH_SHORT).show();
                   userAccount = new UserAccount("error", -1, enterEmail_R.getText().toString(), "error", "error", enterPassword.getText().toString(), "error");

               }

                Database Userdb = new Database(RegisterActivity.this);
                boolean success = Userdb.addUser(userAccount);
                Toast.makeText(RegisterActivity.this, "Success= ", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
