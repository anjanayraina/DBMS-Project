package com.example.dbms_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {


    RecyclerView rView ;
    LinearLayoutManager manager ;
    List<ModelText> userList;
    Adapter adapter;
    public final String dots = "-----------------------------------------------------------------------------";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        initData();
        initRecyclerView();

    }

    private void initRecyclerView(){

rView = findViewById(R.id.recycerView);
manager  = new LinearLayoutManager(this);
manager.setOrientation(rView.VERTICAL);
rView.setLayoutManager(manager);
adapter = new Adapter(userList);


    }

    public void initData(){


        userList = new ArrayList<>();
        userList.add(new ModelText("Hi there !!" , "Anjanay Raina" , "11:34 am" , dots));
        userList.add(new ModelText("Hi there !!" , "Anjanay Raina" , "11:34 am" , dots));
        userList.add(new ModelText("Hi there !!" , "Anjanay Raina" , "11:34 am" , dots));
        userList.add(new ModelText("Hi there !!" , "Anjanay Raina" , "11:34 am" , dots));
        userList.add(new ModelText("Hi there !!" , "Anjanay Raina" , "11:34 am" , dots));
        userList.add(new ModelText("Hi there !!" , "Anjanay Raina" , "11:34 am" , dots));
        userList.add(new ModelText("Hi there !!" , "Anjanay Raina" , "11:34 am" , dots));

    }
}