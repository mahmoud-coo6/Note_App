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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.android.noteapp.MyFirebaseController.getDatabaseReference;

public class home_pages extends AppCompatActivity {
    FirebaseAuth mAuth;
    CategrayAdapter categrayAdapter;
    List<Category> categoryList = new ArrayList<>();
    NotesAdapter notesAdapter;
    List<Note> noteList = new ArrayList<>();
    FirebaseUser currentUser;

    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_pages);
//        mAuth = FirebaseAuth.getInstance();
//        mAuth = MyFirebaseController.getCurrentUserId();
//        FirebaseUser currentUser = mAuth.getCurrentUser();
         currentUser = MyFirebaseController.getCurrentUserId();

            initData();
            initNoteData();

        RecyclerView recyclerView = findViewById(R.id.category_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        categrayAdapter = new CategrayAdapter(this, categoryList);
        recyclerView.setAdapter(categrayAdapter);

        RecyclerView noteRecyclerView = findViewById(R.id.note_rv);
        noteRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        notesAdapter = new NotesAdapter(this, noteList);
        noteRecyclerView.setAdapter(notesAdapter);

        findViewById(R.id.signout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(home_pages.this, MainActivity.class);
                finish();
            }
        });

//        if (currentUser != null) {

            findViewById(R.id.addcatagory_img).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    FirebaseUser currentUser = mAuth.getCurrentUser();
                    Intent intent = new Intent(home_pages.this, CreateNoteCategory.class);
                    intent.putExtra("userId",currentUser.getUid());
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

            findViewById(R.id.show_all_note).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(home_pages.this, NoteList.class);
                    startActivity(intent);
                    finish();
                }
            });

//        } else {
//            throw new Error("cont do this function");
//        }
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
        scoresRef.addValueEventListener(new ValueEventListener() {
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

    private void initNoteData() {
        DatabaseReference scoresRef =  getDatabaseReference();
        scoresRef.child("Note");
        scoresRef.keepSynced(true);
        getDatabaseReference().child("Note").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        noteList.clear();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                            Note note = snapshot.getValue(Note.class);
                            noteList.add(note);

                        }
                        notesAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

    }
}


