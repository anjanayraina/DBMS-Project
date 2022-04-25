package com.example.dbms_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ChatListActivity extends AppCompatActivity implements RecyclerViewInterface {


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

        Button btn  =(Button) findViewById(R.id.button9);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(ChatListActivity.this  , CreateContactAcitivy.class);
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

    @Override
    public void onItemClick(int position) {
        Intent  i = new Intent(ChatListActivity.this ,  MessageActivity.class);
        startActivity(i);

    }
}