package com.example.android.noteapp;

public class User {
    String id;
    String email;
    String categoryId;


    public User(String id, String email, String categoryId) {
        this.id = id;
        this.email = email;
        this.categoryId = categoryId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategratyId(String categoryId) {
        this.categoryId = categoryId;
    }
}

