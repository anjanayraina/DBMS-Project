package com.example.dbms_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class ChatListActivity extends AppCompatActivity {


    RecyclerView rView ;
    LinearLayoutManager manager ;
    List<ChatDisplayModel> userList;
    ChatAdapter adapter;
    public final String dots = "--------------------------------------------------------------------";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);


        initData();
        initRecyclerView();


    }


    public  void initRecyclerView(){

        rView = findViewById(R.id.recycerView);


        manager  = new LinearLayoutManager(this);
        manager.setOrientation(rView.VERTICAL);
        rView.setLayoutManager(manager);
        adapter = new ChatAdapter(userList);
        rView.setAdapter(adapter);


    }

    public void initData(){


        userList = new ArrayList<>();
        userList.add(new ChatDisplayModel("Anjanay Raina" , dots , "23"));
        userList.add(new ChatDisplayModel("Anjanay Raina" , dots , "23"));
        userList.add(new ChatDisplayModel("Anjanay Raina" , dots , "23"));
        userList.add(new ChatDisplayModel("Anjanay Raina" , dots , "23"));
        userList.add(new ChatDisplayModel("Anjanay Raina" , dots , "23"));
        userList.add(new ChatDisplayModel("Anjanay Raina" , dots , "23"));
        userList.add(new ChatDisplayModel("Anjanay Raina" , dots , "23"));



    }

}