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
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.HashMap;

public class CreateGroupChatActivity extends AppCompatActivity {

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

hashMap = new HashMap<>();

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        try {
            Class.forName(className);

            conn = DriverManager.getConnection(url);

        } catch (Exception e) {

            Log.e("connection", String.valueOf(e.getStackTrace()));

        }
        try {
            checkForUser();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        setContentView(R.layout.activity_create_group_chat);
        String uID = getIntent().getStringExtra("userID");

    Button create = (Button) findViewById(R.id.button10);
    EditText text = (EditText) findViewById(R.id.textView8);
    text.setVisibility(View.INVISIBLE);
    EditText groupName = (EditText) findViewById(R.id.textView7);
    Button addUSer= (Button)findViewById(R.id.button34);
    addUSer.setVisibility(View.INVISIBLE);

    create.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            String gname = groupName.getText().toString();
            groupName.setText(gname);
            addUSer.setVisibility(View.VISIBLE);
            text.setVisibility(View.VISIBLE);
            try {
                createGroup(uID  , gname);


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                addUser(uID , 1);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    });

    addUSer.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String uName = text.getText().toString();
            try {
                addUser(hashMap.get(uName) , 0);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    });





    }

    public void addUser(String userID , int isAdmin ) throws SQLException {
        String gId  = getGroupChatID();
        Statement stmt = conn.createStatement();
        String query = String.format(" insert into GroupChatParticipants (userID , groupChatID , joiningTime, isAdmin) values " +
                " (%s , %s , '%s' , %d)" , userID , gId, String.valueOf(new Timestamp(System.currentTimeMillis())) , isAdmin);
        ResultSet res = stmt.executeQuery(query);


    }

    public String  getGroupChatID() throws SQLException {

        Statement stmt = conn.createStatement();
        String  gID= "";
        String query = "select max(groupChatID) as id from Groupchats";
        ResultSet res = stmt.executeQuery(query);
        while(res.next()){
            gID = res.getString("id");
        }

return gID;


    }



    public void createGroup(String userID  , String gName  ) throws SQLException {

        Statement stmt = conn.createStatement();
        String query = String.format("insert into GroupChats(adminId , Groupname )  values (%s , '%s' )",
                userID , gName  ) ;
        ResultSet res = stmt.executeQuery(query);


    }
    public void checkForUser() throws SQLException {



        Statement stmt = conn.createStatement();
        String query = "select * from users ";
        ResultSet res = stmt.executeQuery(query);

        boolean result = false;
        while(res.next()){

            hashMap.put(res.getString("username" ) , res.getString("userID"));

        }



    }

}