package com.example.dbms_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
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
import java.util.ArrayList;
import java.util.List;

public class ChatListActivity extends AppCompatActivity implements RecyclerViewInterface {
    final String ip = "192.168.51.22";
    final String port = "1433";
    final String className = "net.sourceforge.jtds.jdbc.Driver";
    final String dataBaseName = "newDB";
    final String userName = "admin";
    final String password = "admin";
    //final String url = "jdbc:jtds:sqlserver://"+ip+":"+port+";"+"databasename" + dataBaseName + "; user="+ userName +";"+"password" ;
    final String url = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";" + "databasename=" + dataBaseName + ";user=" + userName + ";password=" + password + ";";
    Connection conn = null;

    RecyclerView rView ;
    LinearLayoutManager manager ;
    List<ChatDisplayModel> userList;
    ChatAdapter adapter;
    public final String dots = "--------------------------------------------------------------------";
    String uid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        try {
            Class.forName(className);

            conn = DriverManager.getConnection(url);

        } catch (Exception e) {

            Log.e("connection", String.valueOf(e.getStackTrace()));

        }

        uid = getIntent().getStringExtra("userID");


        try {
            initData();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        initRecyclerView();

        Button btn  =(Button) findViewById(R.id.button9);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(ChatListActivity.this  , CreateContactAcitivy.class);
                startActivity(i);

            }
        });

        Button group  = (Button) findViewById(R.id.button11);
        group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ChatListActivity.this , CreateGroupChatActivity.class);
                startActivity(i);

            }
        });


    }


    public  void initRecyclerView(){

        rView = findViewById(R.id.recycerView);


        manager  = new LinearLayoutManager(this);
        manager.setOrientation(rView.VERTICAL);
        rView.setLayoutManager(manager);
        adapter = new ChatAdapter(userList , this);
        rView.setAdapter(adapter);


    }

    public void initData() throws SQLException {


        userList = new ArrayList<>();

        String query = "select * from directchats where userid = " + uid + " OR where contactID = " + uid;
        Statement stmt = conn.createStatement();

        ResultSet res = stmt.executeQuery(query);

        while(res.next()){

            String newStmt = "select username from users where userid = " + res.getString(3);
            ResultSet tempSet = conn.createStatement().executeQuery(newStmt);
            userList.add(new ChatDisplayModel(tempSet.getString(1).toString() , dots , uid , res.getString(3)));

        }




    }

    @Override
    public void onItemClick(int position) {
        Intent  i = new Intent(ChatListActivity.this ,  MessageActivity.class);
        startActivity(i);

    }
}