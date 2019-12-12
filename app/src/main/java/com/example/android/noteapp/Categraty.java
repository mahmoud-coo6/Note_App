package com.example.android.noteapp;

public class Categraty {
    String title;
    Integer id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    String color;

    public Categraty(String title, Integer id, String color) {
        this.title = title;
        this.id = id;
        this.color = color;
    }
}

