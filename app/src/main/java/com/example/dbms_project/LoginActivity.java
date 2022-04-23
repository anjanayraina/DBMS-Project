package com.example.dbms_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.sql.Connection;

public class LoginActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_login);
    }
}