package com.example.dbms_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MessageActivity extends AppCompatActivity {



    RecyclerView rView ;
    LinearLayoutManager manager ;
    List<ModelText> userList;
    Adapter adapter;
    public final String dots = "--------------------------------------------------------------------";

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
      rView.setAdapter(adapter);


    }

    public void initData(){


        userList = new ArrayList<>();
        userList.add(new ModelText("Hi there kjvbsdj;slbgsoj glsbgos gkmsbgiosr gmrsnogj rsm;g rsjkg rsmrs grs gjl;sr gjl rsh;g rsj ghrbg rkjgb rs gmrs gosr gnkmbsrug srbgjsr hgarhgrhgrohgruobgklrkbugobrsg lrsk glrs !!" , "Anjanay Raina" , "11:34 am" , dots));
        userList.add(new ModelText("Hi there !!" , "Anjanay Raina" , "11:34 am" , dots));
        userList.add(new ModelText("Hi there !!" , "Anjanay Raina" , "11:34 am" , dots));
        userList.add(new ModelText("Hi there !!" , "Anjanay Raina" , "11:34 am" , dots));
        userList.add(new ModelText("Hi there !!" , "Anjanay Raina" , "11:34 am" , dots));
        userList.add(new ModelText("Hi there !!" , "Anjanay Raina" , "11:34 am" , dots));
        userList.add(new ModelText("Hi there !!" , "Anjanay Raina" , "11:34 am" , dots));

    }
}