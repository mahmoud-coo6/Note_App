package com.example.android.noteapp;

import java.util.ArrayList;

public class User {
    Integer id;
    String email;
    ArrayList<Categraty> categraty;

    public User(Integer id, String email, ArrayList<Categraty> categraty) {
        this.id = id;
        this.email = email;
        this.categraty = categraty;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Categraty> getCategraty() {
        return categraty;
    }

    public void setCategraty(ArrayList<Categraty> categraty) {
        this.categraty = categraty;
    }
}

