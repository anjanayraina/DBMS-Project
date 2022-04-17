package com.example.dbms_project;

import android.widget.ImageView;

public class ModelText {

    private int image;
    private String text;
    private String name;
    private String time;
    private String line;


    public ModelText(int image, String text, String name, String time, String line) {
        this.image = image;
        this.text = text;
        this.name = name;
        this.time = time;
        this.line = line;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
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
