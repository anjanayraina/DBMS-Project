package com.example.dbms_project;

import android.widget.ImageView;

public class ModelText {


    private String text;
    private String name;
    private String time;
    private String line;


    public ModelText(String text, String name, String time, String line) {

        this.text = text;
        this.name = name;
        this.time = time;
        this.line = line;
    }




    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }
}
