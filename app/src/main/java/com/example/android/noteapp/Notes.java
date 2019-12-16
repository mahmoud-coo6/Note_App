package com.example.android.noteapp;

import java.util.Date;


public class Notes {
    String title;
    String body;
    Color color;
    Date date;


    public Notes(String title, String body, Color color, Date date) {
        this.title = title;
        this.body = body;
        this.color = color;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}


