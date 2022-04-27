package com.example.dbms_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AllMessagesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_messages);

    TextView array [] = new TextView[24];
    String temp = "textView";

    for(int i=21;i<=43;i++){

        String t = temp + i;
        int resID = getResources().getIdentifier(t, "id", getPackageName());
        TextView btn = findViewById(resID);
        btn.setVisibility(View.GONE);
    }





    }
}