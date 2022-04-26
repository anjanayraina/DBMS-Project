package com.example.dbms_project;

import android.view.View;
import android.widget.ImageView;

public class ChatDisplayModel {

    private String name;

    private String line;

    private String userID;

    public String getContactID() {
        return contactID;
    }

    public void setContactID(String contactID) {
        this.contactID = contactID;
    }

    private String contactID;


    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public ChatDisplayModel(String name, String line  , String userID , String contactID) {

        this.name = name;
        this.line = line;
        this.userID = userID;
        this.contactID = contactID;


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
