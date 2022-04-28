package com.example.dbms_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MessageDeleteActivity extends AppCompatActivity {
    final String ip = "192.168.51.22";
    final String port = "1433";
    final String className = "net.sourceforge.jtds.jdbc.Driver";
    final String dataBaseName = "newDB";
    final String userName = "admin";
    final String password = "admin";
    //final String url = "jdbc:jtds:sqlserver://"+ip+":"+port+";"+"databasename" + dataBaseName + "; user="+ userName +";"+"password" ;
    final String url = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";" + "databasename=" + dataBaseName + ";user=" + userName + ";password=" + password + ";";
    Connection conn = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_delete);

        String messageId = getIntent().getStringExtra("messageID");
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        try {
            Class.forName(className);

            conn = DriverManager.getConnection(url);

        } catch (Exception e) {

            Log.e("connection", String.valueOf(e.getStackTrace()));

        }

        String query = "DELETE FROM messages WHERE messageID =  " + messageId;
        Statement stmt = null;

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

        Toast.makeText(getApplicationContext() , "Message Deleted" , Toast.LENGTH_SHORT);


    }
}