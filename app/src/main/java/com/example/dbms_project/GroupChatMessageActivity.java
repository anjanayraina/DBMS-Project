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
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.HashMap;

public class GroupChatMessageActivity extends AppCompatActivity {
    String uid = "";
    String gID = "";
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
        setContentView(R.layout.activity_group_chat_message);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        try {
            Class.forName(className);

            conn = DriverManager.getConnection(url);

        } catch (Exception e) {

            Log.e("connection", String.valueOf(e.getStackTrace()));

        }
        String temp = "textView";
        uid = getIntent().getStringExtra("userID");
      gID = getIntent().getStringExtra("groupChatID");
        hashMap = (HashMap<String, String>) getIntent().getExtras().get("hashMap");

Log.e("Error" , gID);
        TextView array[] = new TextView[30];


        for (int i = 21; i <= 42; i++) {
            String button = temp + i;

            int resID = getResources().getIdentifier(button, "id", getPackageName());
            TextView btn = findViewById(resID);
            btn.setVisibility(View.INVISIBLE);
            array[i - 21] = btn;

        }

        String query = "select * from messages where groupChatID = " + gID;
        Statement stmt = null;

        Button settings  = (Button)findViewById(R.id.button36);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GroupChatMessageActivity.this , GroupSettings.class);
                i.putExtra("groupChatID", gID);
                i.putExtra("userID" , uid);
                startActivity(i);

            }
        });

        try {
            stmt = conn.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        ResultSet res = null;
        try {
            res = stmt.executeQuery(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        int count = 0;

        if (res != null) {
            while (true) {
                try {
                    if (!res.next()) break;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }


                try {
                    String messageID = res.getString("messageID");
                    String userID = res.getString("senderID");
                    String messageStatus = res.getString("messageStatus");
                    array[count].setText(hashMap.get(userID) + "\n" + res.getString("body") + "   " + res.getString("SendingTime"));
                    array[count].setVisibility(View.VISIBLE);
                    array[count].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(GroupChatMessageActivity.this,  MessageDeleteActivity.class);
                            i.putExtra("messageID" , messageID);
                            startActivity(i);
                        }
                    });
                    count++;

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }

        Button button = (Button)findViewById(R.id.button25);
        EditText text = (EditText)findViewById(R.id.textView51) ;

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = String.format("insert into messages (senderID  , body , sendingTime , groupChatID , DeleteMessage , MessageStatus) values (%s , '%s' , '%s' , %s , %d , %d)  " ,
                        uid  , text.getText().toString() , String.valueOf(new Timestamp(System.currentTimeMillis())), gID, 0, 1);
                Statement stmt = null;
                try {
                    stmt = conn.createStatement();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                try {
                    ResultSet res = stmt.executeQuery(query);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                text.setText("");
            }
        });


        TextView refresh =  (TextView) findViewById(R.id.textView50);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recreate();
            }
        });


    }
}