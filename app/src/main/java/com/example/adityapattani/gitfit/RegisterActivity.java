package com.example.adityapattani.gitfit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText username, password, confpass, age_text;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        username = (EditText) findViewById(R.id.username_register);
        password = (EditText) findViewById(R.id.passwd_register);
        confpass = (EditText) findViewById(R.id.confp_register);
        age_text = (EditText) findViewById(R.id.age);
        register = (Button) findViewById(R.id.register_register);
        final DBHelper dbHelper = new DBHelper(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname, pwd, cpwd, age;
                uname = username.getText().toString().trim();
                pwd = password.getText().toString().trim();
                cpwd = confpass.getText().toString().trim();
                age = age_text.getText().toString().trim();

                if (uname.equals("") || pwd.equals("") || cpwd.equals("") || age.equals("")){
                    Toast.makeText(getApplicationContext(), "Enter your details", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (!pwd.equals(cpwd)){
                        Toast.makeText(getApplicationContext(), "Password and Confirm Password do not match", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        dbHelper.open();
                        dbHelper.insertUser(uname, pwd, age);
                        dbHelper.close();
                        Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });
    }
}
