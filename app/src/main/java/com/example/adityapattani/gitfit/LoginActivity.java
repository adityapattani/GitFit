package com.example.adityapattani.gitfit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText username, passwd;
    Button login, register;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        String shared_name, shared_pwd, SP_name;
        SP_name = "MyPref";

        final SharedPreferences sharedPreferences = getSharedPreferences(SP_name, Context.MODE_PRIVATE);
        shared_name = sharedPreferences.getString("Name", null);
        shared_pwd = sharedPreferences.getString("Pass", null);
        final DBHelper dbHelper = new DBHelper(this);

        if (shared_name != null){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }

        username = (EditText) findViewById(R.id.username);
        passwd = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);

        username.setText(shared_name);
        passwd.setText(shared_pwd);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname, pwd;
                uname = username.getText().toString().trim();
                pwd = passwd.getText().toString().trim();

                if (uname.equals("") || pwd.equals("")){
                    Toast.makeText(getApplicationContext(), "Provide all the details", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (dbHelper.CheckUser(uname, pwd)){
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("Name", uname);
                        editor.putString("Pass", pwd);
                        editor.commit();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "User not found", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


    }
}
