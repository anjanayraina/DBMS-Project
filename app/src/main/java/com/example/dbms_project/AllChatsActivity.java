package com.example.dbms_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class AllChatsActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_all_chats);
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
        String contact = getIntent().getStringExtra("contactID");
        hashMap = (HashMap<String, String>) getIntent().getExtras().get("hashMap");
        String directChatID = getIntent().getStringExtra("directChatID");

        TextView array [] = new TextView[30];


        for(int i=21;i<=42;i++){
            String button = temp + i;

            int resID = getResources().getIdentifier(button, "id", getPackageName());
            Button btn = findViewById(resID);
            btn.setVisibility(View.INVISIBLE);
            array[i-21] = btn;

        }

        String query = "select * from messages ";
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


                try{
                    Toast.makeText(getApplicationContext() , "This Activity Called !!" , Toast.LENGTH_LONG).show();
                    String messageID = res.getString("messageID");
                    String userID = res.getString("senderID");
                    String messageStatus = res.getString("messageStatus");

                    if(directChatID.equals(res.getString("directChatID"))) {
                        array[count].setText(hashMap.get(userID) + "\n" + res.getString("body"));
                        array[count].setVisibility(View.VISIBLE);
                        count++;
                    }
                }

                catch (Exception e){
                    e.printStackTrace();
                }

            }

        }


    }
}