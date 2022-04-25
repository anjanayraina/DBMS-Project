package com.example.dbms_project;

import android.view.View;
import android.widget.ImageView;

public class ChatDisplayModel {



    private String name;

    private String line;

    private String userID;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public ChatDisplayModel(String name, String line  , String userID) {

        this.name = name;
        this.line = line;

        this.userID = userID;

    }





    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }
}
