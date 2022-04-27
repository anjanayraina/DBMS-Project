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

public class AllChats extends AppCompatActivity {

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
        setContentView(R.layout.activity_all_chats2);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        try {
            Class.forName(className);

            conn = DriverManager.getConnection(url);

        } catch (Exception e) {

            Log.e("connection", String.valueOf(e.getStackTrace()));

        }
        String temp = "button";
        uid = getIntent().getStringExtra("userID");
        hashMap = (HashMap<String, String>) getIntent().getExtras().get("hashMap");
        Button array [] = new Button[8];
        Button newChat = findViewById(R.id.button14);
        newChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(AllChats.this , AllMessagesActivity.class);
                i.putExtra("hashMap" , hashMap);
                i.putExtra("userID" , uid);

                startActivity(i);
            }
        });

        for(int i=16;i<24;i++){
         String button = temp + i;

            int resID = getResources().getIdentifier(button, "id", getPackageName());
            Button btn = findViewById(resID);
            btn.setVisibility(View.GONE);


            array[i-16] = btn;

        }

        String query = "select * from directchats ";
        Statement stmt = null;


        try {
            stmt = conn.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        ResultSet res =null;
        try {
            res = stmt.executeQuery(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        int count = 0;

        if(res != null) {
            while (true) {
                try {
                    if (!res.next()) break;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                try {
                    String contact =  res.getString("contactID");
                    String user = res.getString("userID");
                    if(res.getString("userid").equals(uid) ){


                        array[count].setText(hashMap.get(res.getString("contactid")));
                        array[count].setVisibility(View.VISIBLE);
                        array[count].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent i = new Intent(AllChats.this , AllMessagesActivity.class);
                                i.putExtra("hashMap" , hashMap);
                                i.putExtra("userID" , uid);
                                i.putExtra("contactID" ,contact);
                                startActivity(i);
                            }
                        });
                        count++;
                    }

                    else if(res.getString("contactid").equals(uid)){

                        array[count].setText(hashMap.get(res.getString("userid")));
                        array[count].setVisibility(View.VISIBLE);
                        array[count].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent i = new Intent(AllChats.this , AllMessagesActivity.class);
                                i.putExtra("hashMap" , hashMap);
                                i.putExtra("userID" , uid);
                                i.putExtra("contactID" ,user);
                                startActivity(i);
                            }
                        });
                    count++;

                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

                }

            }
}

