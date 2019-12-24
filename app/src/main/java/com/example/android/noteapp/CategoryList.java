package com.example.android.noteapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
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
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.android.noteapp.MyFirebaseController.getDatabaseReference;

public class CategoryList extends AppCompatActivity {

    CategrayAdapter categrayAdapter;
    List<Category> categoryList = new ArrayList<>();
    DatabaseReference mSearchedReference;
    EditText search;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

            initData();

        int columns = 3;
        RecyclerView recyclerView = findViewById(R.id.category_rv);
        LinearLayoutManager layoutManager = new GridLayoutManager(this, columns);
        recyclerView.setLayoutManager(layoutManager);
        categrayAdapter = new CategrayAdapter(this, categoryList);
        recyclerView.setAdapter(categrayAdapter);
        search = findViewById(R.id.search);
        search.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                // you can call or do what you want with your EditText here
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                initSearch(search.getText().toString());
            }
        });

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryList.this, home_pages.class);
                startActivity(intent);
                finish();
            }
        });

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryList.this, CreateNoteCategory.class);
                startActivity(intent);
                finish();
            }
        });

        findViewById(R.id.view_art_bord).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryList.this, ViewArtBoard.class);
                startActivity(intent);
                finish();
            }
        });


    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    private void initData() {

        DatabaseReference scoresRef =  getDatabaseReference();
        scoresRef.child("Category");
        scoresRef.keepSynced(true);
        getDatabaseReference().child("Category").addValueEventListener(new ValueEventListener() {
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

    private void initSearch(final String search) {
        FirebaseDatabase.getInstance().getReference().child("Category")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        categoryList.clear();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                            Category category = snapshot.getValue(Category.class);
                            if (category.getTitle().toLowerCase().contains(search.toLowerCase()) || search.trim().length() == 0) {
                                categoryList.add(category);
                            }
                        }
                        categrayAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

    }

}
