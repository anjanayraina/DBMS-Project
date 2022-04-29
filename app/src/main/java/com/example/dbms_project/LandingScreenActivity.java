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
import java.util.ArrayList;
import java.util.HashMap;

public class LandingScreenActivity extends AppCompatActivity {
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
    ArrayList<String> groupChatHash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_screen);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();


        StrictMode.setThreadPolicy(policy);

        Button settings = (Button) findViewById(R.id.button15);

        try {
            Class.forName(className);

            conn = DriverManager.getConnection(url);

        } catch (Exception e) {

            Log.e("connection", String.valueOf(e.getStackTrace()));

        }
        Statement stmt = null;
        ResultSet res = null;
        try {
            stmt = conn.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String query = "select * from GroupChatparticipants ";
        try {
             res = stmt.executeQuery(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        Button directChat = (Button) findViewById(R.id.button28);

        Button groupChat = (Button) findViewById(R.id.button29);
        uid = getIntent().getStringExtra("userID");
        hashMap = (HashMap<String, String>) getIntent().getExtras().get("hashMap");
        groupChatHash = new ArrayList<>();


        while(true){
            try {
                if (!res.next()) break;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            try {
                if(res.getString("userID").equals(uid))groupChatHash.add(res.getString("GroupChatID"));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  i = new Intent(LandingScreenActivity.this,  SettingsActivity.class);
                i.putExtra("userID", uid);
                i.putExtra("hashMap" , hashMap);

                startActivity(i);
            }
        });
        directChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  i = new Intent(LandingScreenActivity.this,  AllChats.class);
                i.putExtra("userID", uid);
                i.putExtra("hashMap" , hashMap);

                startActivity(i);
            }
        });

        groupChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  i = new Intent(LandingScreenActivity.this,  AllGroupList.class);
                i.putExtra("userID", uid);
                i.putExtra("hashMap" , hashMap);
                i.putExtra("groupChatList" , groupChatHash);
                startActivity(i);
            }
        });

        Button delete = (Button)findViewById(R.id.button30);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  i = new Intent(LandingScreenActivity.this,  DeleteUserActivity.class);
                i.putExtra("userID", uid);
                i.putExtra("hashMap" , hashMap);

                startActivity(i);
            }
        });
    }
}