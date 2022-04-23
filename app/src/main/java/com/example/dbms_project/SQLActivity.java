package com.example.dbms_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLActivity extends AppCompatActivity {
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
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlactivity);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET} , PackageManager.PERMISSION_GRANTED);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        try {
            Class.forName(className);

            conn  = DriverManager.getConnection(url );

        }

        catch (Exception e){

            Log.e("connection" , String.valueOf(e.getStackTrace()) );

        }

        try {
            sqlButton();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            sqlButton();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void sqlButton() throws SQLException {

        TextView button = (TextView)findViewById(R.id.firstName);

        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery("select * from Table_1");
        if(conn!=null) {

            while(rs.next()) {
                button.setText(String.valueOf(rs.getString(1)));
            }
        }
        else{

            button.setText("Connection is null");
        }



    }


}