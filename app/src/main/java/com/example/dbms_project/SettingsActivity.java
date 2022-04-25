package com.example.dbms_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        EditText profileStatus = (EditText) findViewById(R.id.button3);
        EditText firstName = (EditText) findViewById(R.id.button12);
        EditText lastName = (EditText) findViewById(R.id.button5);
        EditText phoneNumber = (EditText) findViewById(R.id.button6);
        EditText userName = (EditText) findViewById(R.id.button4);
        Button save = (Button) findViewById(R.id.button7);
        String status = profileStatus.getText().toString();
        String userNameString = userName.getText().toString();
        String phoneNumberString  = phoneNumber.getText().toString();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }


    public boolean checkIfValuesAreUnique(){


        return true;

    }
    public void updateValues(){


    }
}