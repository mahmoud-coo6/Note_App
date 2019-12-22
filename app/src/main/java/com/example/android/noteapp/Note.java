package com.example.android.noteapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Note implements Parcelable {
    public static final Parcelable.Creator<Note> CREATOR = new Parcelable.Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel source) {
            return new Note(source);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };
    String id;
    String title;
    String body;
    int color;
    long createdAt;
    long lastUpdate;
    String categoryId;

    public Note() {

    }

    public Note(String id, String title, String body, int color, long createdAt, long lastUpdate, String categoryId) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.color = color;
        this.createdAt = createdAt;
        this.lastUpdate = lastUpdate;
        this.categoryId = categoryId;
    }

    protected Note(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.body = in.readString();
        this.color = in.readInt();
        this.createdAt = in.readLong();
        this.lastUpdate = in.readLong();
        this.categoryId = in.readString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.body);
        dest.writeInt(this.color);
        dest.writeLong(this.createdAt);
        dest.writeLong(this.lastUpdate);
        dest.writeString(this.categoryId);
    }
}


