package com.example.android.noteapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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

public class CategoryNote extends AppCompatActivity {

    Intent intent;
    Category category;
    NotesAdapter noteAdapter;
    List<Note> noteList = new ArrayList<>();
    DatabaseReference mSearchedReference;
    EditText search;
    String categoryId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.empty_note);

        intent = getIntent();
        if (intent.hasExtra(CategrayAdapter.CATEGORY_TRANSFER)) {
            List<Category> noteList = new ArrayList<>();
            noteList = intent.getParcelableArrayListExtra(CategrayAdapter.CATEGORY_TRANSFER);
            category = noteList.get(intent.getIntExtra(CategrayAdapter.CATEGORY_POSITION, 0));
            Log.i("testttting", "onCreate: myid1 " + category.getId());
            categoryId = category.getId();
        } else if (intent.hasExtra("CategoryId")) {
            categoryId = intent.getStringExtra("CategoryId");
            Log.i("testttting", "onCreate: myid2 " + categoryId);
        }

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryNote.this, EditNote.class);
                intent.putExtra("new", true);
                intent.putExtra("CategoryId", categoryId);
                startActivity(intent);
                finish();
            }
        });

        initData();

        RecyclerView recyclerView = findViewById(R.id.category_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        noteAdapter = new NotesAdapter(this, noteList);
        recyclerView.setAdapter(noteAdapter);

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


    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    private void initData() {
        DatabaseReference scoresRef = getDatabaseReference();
        scoresRef.child("Note").orderByChild("categoryId").equalTo(categoryId);
        scoresRef.keepSynced(true);
        getDatabaseReference().child("Note").orderByChild("categoryId").equalTo(categoryId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        noteList.clear();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                            Note note = snapshot.getValue(Note.class);
                            noteList.add(note);

                        }
                        noteAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    private void initSearch(final String search) {
        FirebaseDatabase.getInstance().getReference().child("Note").orderByChild("categoryId").equalTo(categoryId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        noteList.clear();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                            Note note = snapshot.getValue(Note.class);
                            if (note.getTitle().toLowerCase().contains(search.toLowerCase()) || search.trim().length() == 0) {
                                noteList.add(note);
                            }
                        }
                        noteAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

    }

}
