package com.example.dbms_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity {

    private TextView  userName ;
    private TextView password;
    public boolean checkUserName(String s){



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        userName = (TextView) findViewById(R.id.editText);
        password =(TextView) findViewById(R.id.editText2);

        String userNameString  =userName.toString();
        String passwordString = password.toString();

        Intent i  = new Intent(this, CreateUserActivity.class);
        startActivity(i);

    }
}