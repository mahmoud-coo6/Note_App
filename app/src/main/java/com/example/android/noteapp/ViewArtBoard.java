package com.example.android.noteapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.android.noteapp.MyFirebaseController.getDatabaseReference;

public class ViewArtBoard extends AppCompatActivity {

    CategrayAdapter categrayAdapter;
    List<Category> categoryList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.art_board);

        if (isNetworkAvailable()) {
            initData();
        } else {
            Toast.makeText(this, "there is no interent connection.", Toast.LENGTH_SHORT).show();
        }

        int columns = 4;
        RecyclerView recyclerView = findViewById(R.id.category_rv);
        LinearLayoutManager layoutManager = new GridLayoutManager(this, columns);
        recyclerView.setLayoutManager(layoutManager);
        categrayAdapter = new CategrayAdapter(this, categoryList);
        recyclerView.setAdapter(categrayAdapter);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    private void initData() {
        DatabaseReference scoresRef = getDatabaseReference().child("Category");
        scoresRef.keepSynced(true);
        getDatabaseReference().child("Category")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        categoryList.clear();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                            Category category = snapshot.getValue(Category.class);
                            categoryList.add(category);

                        }
                        categrayAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

}
