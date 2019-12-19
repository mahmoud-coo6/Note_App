package com.example.android.noteapp;

import java.util.ArrayList;


public class Categraty {
    String title;
    Integer id;
    Color color;
    ArrayList<Notes> notes;

    public Categraty(String title, Integer id, Color color, ArrayList<Notes> notes) {
        this.title = title;
        this.id = id;
        this.color = color;
        this.notes = notes;
    }

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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public ArrayList<Notes> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<Notes> notes) {
        this.notes = notes;
    }

}

