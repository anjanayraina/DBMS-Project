package com.example.dbms_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
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
import java.sql.SQLException;
import java.sql.Statement;

public class CreateUserActivity extends AppCompatActivity {

    final String ip = "192.168.51.22";
    final String port = "1433";
    final String className  =  "net.sourceforge.jtds.jdbc.Driver";
    final String dataBaseName = "newDB";
    final String  userName= "admin";
    final String password=  "admin";
    //final String url = "jdbc:jtds:sqlserver://"+ip+":"+port+";"+"databasename" + dataBaseName + "; user="+ userName +";"+"password" ;
    final String url = "jdbc:jtds:sqlserver://"+ ip + ":"+ port+";"+ "databasename="+ dataBaseName+";user="+userName+";password="+password+";";
    Connection conn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);


        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET} , PackageManager.PERMISSION_GRANTED);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        Button quit = (Button)findViewById(R.id.button2);
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuitApp();
            }
        });
        StrictMode.setThreadPolicy(policy);

        try {
            Class.forName(className);

            conn  = DriverManager.getConnection(url );

        }

        catch (Exception e){

            Log.e("connection" , String.valueOf(e.getStackTrace()) );

        }

        Button create = (Button) findViewById(R.id.button);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    Toast.makeText(getApplicationContext() , "Toast" , Toast.LENGTH_SHORT);

                    createUser();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });


    }

    public void QuitApp() {
        CreateUserActivity.this.finish();
        System.exit(0);
    }


    public void createUser() throws SQLException {

        String firstName = ((EditText)findViewById(R.id.editText)).getText().toString();
        String lastName  = ((EditText)findViewById(R.id.editText4)).getText().toString();
        int age = Integer.parseInt(((EditText)findViewById(R.id.editText5)).getText().toString());
        String phoneNumber= ((EditText)findViewById(R.id.editText3)).getText().toString();
        String userName  = ((EditText)findViewById(R.id.editText6)).getText().toString();
        String password = ((EditText)findViewById(R.id.editText7)).getText().toString();


        Statement stmt = conn.createStatement();

        @SuppressLint("DefaultLocale") String query = String.format("insert into SignUpTable (firstName , lastName  , age , phoneNumber , userName , password) values" +
                " ( '%s' , '%s' , %d , '%s' , '%s' , '%s') ;" , firstName , lastName , age , phoneNumber , userName   ,password);
        stmt.executeQuery(query);

    }
}