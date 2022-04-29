package com.example.dbms_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class GroupSettings extends AppCompatActivity {

    String uid = "";
    final String ip = "192.168.51.22";
    final String port = "1433";
    final String className = "net.sourceforge.jtds.jdbc.Driver";
    final String dataBaseName = "newDB";
    final String userName = "admin";
    final String password = "admin";
    //final String url = "jdbc:jtds:sqlserver://"+ip+":"+port+";"+"databasename" + dataBaseName + "; user="+ userName +";"+"password" ;
    final String url = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";" + "databasename=" + dataBaseName + ";user=" + userName + ";password=" + password + ";";
    Connection conn = null;
    HashMap<String , String> hashMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_settings);

        Button deleteGroup = (Button)findViewById(R.id.button4);
        Button addMember = (Button) findViewById(R.id.button5);
        Button removeUser= (Button) findViewById(R.id.button6);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        String uid = getIntent().getStringExtra("userID");

        String groupID = getIntent().getStringExtra("GroupID");

        try {
            Class.forName(className);

            conn = DriverManager.getConnection(url);

        } catch (Exception e) {

            Log.e("connection", String.valueOf(e.getStackTrace()));

        }


        deleteGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent i = new Intent(GroupSettings.this , DeleteGroup.class);
               i.putExtra("GroupID" , groupID);
               startActivity(i);
            }

        });


    }
}