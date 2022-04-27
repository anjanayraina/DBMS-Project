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
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginActivity extends AppCompatActivity {

    final String ip = "192.168.51.22";
    final String port = "1433";
    final String className = "net.sourceforge.jtds.jdbc.Driver";
    final String dataBaseName = "newDB";
    final String userName = "admin";
    final String password = "admin";
    //final String url = "jdbc:jtds:sqlserver://"+ip+":"+port+";"+"databasename" + dataBaseName + "; user="+ userName +";"+"password" ;
    final String url = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";" + "databasename=" + dataBaseName + ";user=" + userName + ";password=" + password + ";";
    Connection conn = null;
    String uid = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        try {
            Class.forName(className);

            conn = DriverManager.getConnection(url);

        } catch (Exception e) {

            Log.e("connection", String.valueOf(e.getStackTrace()));

        }

        Button button  =(Button) findViewById(R.id.button);

        Button create = (Button)findViewById(R.id.button8);

        Button quit = (Button)findViewById(R.id.button2);

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuitApp();
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i  =new Intent(LoginActivity.this,  CreateUserActivity.class );
                startActivity(i);
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean temp = true;
                try {
                    temp = checkForUser();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();

                }

                if(!temp){
                    Toast toast = Toast.makeText(getApplicationContext() , "User Not Found ",  Toast.LENGTH_SHORT);
                    toast.show();

                }

                else {
                    Toast toast = Toast.makeText(getApplicationContext() , "User Verified ",  Toast.LENGTH_SHORT);
                    toast.show();
                    Intent  i = new Intent(LoginActivity.this,  ChatListActivity.class);
                    i.putExtra("userID", uid);
                    startActivity(i);

                }
            }
        });






    }

    public void QuitApp() {
        LoginActivity.this.finish();
        System.exit(0);
    }

    public boolean checkForUser() throws SQLException {


        String name = ((EditText) findViewById(R.id.editText)).getText().toString();
        String pass = ((EditText) findViewById(R.id.editText2)).getText().toString();
        Statement stmt = conn.createStatement();
        String query = "select * from users ";
        ResultSet res = stmt.executeQuery(query);

        while(res.next()){

            if(String.valueOf(res.getString("username")).equals(name) && String.valueOf(res.getString("password")).equals(pass)){

                uid = String.valueOf(res.getString(1));
                return true;

            }

        }



      return false;


    }


}