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
import android.widget.EditText;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.HashMap;

public class AddGroupMember extends AppCompatActivity {
    final String ip = "192.168.51.22";
    final String port = "1433";
    final String className = "net.sourceforge.jtds.jdbc.Driver";
    final String dataBaseName = "newDB";
    final String userName = "admin";
    final String password = "admin";
    //final String url = "jdbc:jtds:sqlserver://"+ip+":"+port+";"+"databasename" + dataBaseName + "; user="+ userName +";"+"password" ;
    final String url = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";" + "databasename=" + dataBaseName + ";user=" + userName + ";password=" + password + ";";
    Connection conn = null;

    public HashMap<String , String > hashMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group_member);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        try {
            Class.forName(className);

            conn = DriverManager.getConnection(url);

        } catch (Exception e) {

            Log.e("connection", String.valueOf(e.getStackTrace()));

        }
        String uid = getIntent().getStringExtra("userID");
        String groupID = getIntent().getStringExtra("groupID");

        EditText user = (EditText) findViewById(R.id.textView13);
        Button create = (Button )findViewById(R.id.button33);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    addUser(uid , 0 , groupID);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });


    }
    public void addUser(String userID , int isAdmin  , String groupID) throws SQLException {

        Statement stmt = conn.createStatement();
        String query = String.format(" insert into GroupChatParticipants (userID , groupChatID , joiningTime, isAdmin) values " +
                " (%s , %s , '%s' , %d)" , userID , groupID, String.valueOf(new Timestamp(System.currentTimeMillis())) , isAdmin);
        ResultSet res = stmt.executeQuery(query);


    }

}