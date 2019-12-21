package com.example.android.noteapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Category implements Parcelable {
    public static final Parcelable.Creator<Category> CREATOR = new Parcelable.Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel source) {
            return new Category(source);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };
    String id;
    String title;
    int color;
    long createdAt;
    long lastUpdate;

    public Category() {

    }

    public Category(String id, String title, int color, long createdAt, long lastUpdate) {
        this.id = id;
        this.title = title;
        this.color = color;
        this.createdAt = createdAt;
        this.lastUpdate = lastUpdate;
    }

    protected Category(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.color = in.readInt();
        this.createdAt = in.readLong();
        this.lastUpdate = in.readLong();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeInt(this.color);
        dest.writeLong(this.createdAt);
        dest.writeLong(this.lastUpdate);
    }
}

