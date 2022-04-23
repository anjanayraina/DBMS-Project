package com.example.dbms_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity {

    private TextView  userName ;
    private TextView password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        userName = (EditText) findViewById(R.id.userName);
        password =(EditText) findViewById(R.id.password);

        String userNameString  =userName.toString();
        String passwordString = password.toString();

        Intent i  = new Intent(this, CreateUserActivity.class);
        startActivity(i);

    }
}