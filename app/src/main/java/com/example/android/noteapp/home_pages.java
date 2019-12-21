package com.example.android.noteapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class home_pages extends AppCompatActivity {
    ImageView addcatagory_img;
    FirebaseAuth mAuth;
    CategrayAdapter categrayAdapter;
    List<Category> categoryList = new ArrayList<>();

    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_pages);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

//        ImageView ivVectorImage = findViewById(R.id.centernote);
//        VectorChildFinder vector = new VectorChildFinder(this, R.drawable.ic_links_notebook, ivVectorImage);
//        VectorDrawableCompat.VFullPath path1 = vector.findPathByName("path1");
//        path1.setFillColor(getResources().getColor(android.R.color.holo_red_light));

        if (isNetworkAvailable()) {
            initData();
        } else {
            Toast.makeText(this, "there is no interent connection.", Toast.LENGTH_SHORT).show();
        }

        RecyclerView recyclerView = findViewById(R.id.category_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        categrayAdapter = new CategrayAdapter(this, categoryList);
        recyclerView.setAdapter(categrayAdapter);

        findViewById(R.id.signout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(home_pages.this, MainActivity.class);
                finish();
            }
        });

        if (currentUser != null) {

//            FloatingActionButton fab = findViewById(R.id.fab);
//            fab.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(home_pages.this, NoteList.class);
//                    startActivity(intent);
//                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                            .setAction("Action", null).show();
//                }
//            });


            findViewById(R.id.addcatagory_img).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FirebaseUser currentUser = mAuth.getCurrentUser();
                    Intent intent = new Intent(home_pages.this, CreateNoteCategory.class);
                    startActivity(intent);
                    finish();

                }
            });

            findViewById(R.id.show_all_category).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(home_pages.this, CategoryList.class);
                    startActivity(intent);
                    finish();
                }
            });


        } else {
            throw new Error("cont do this function");
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void initData() {
        FirebaseDatabase.getInstance().getReference().child("Category")
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


