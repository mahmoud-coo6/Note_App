package com.example.android.noteapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
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

public class NoteList extends AppCompatActivity {

    NotesAdapter noteAdapter;
    List<Note> noteList = new ArrayList<>();
    DatabaseReference mSearchedReference;
    EditText search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_list);

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
        DatabaseReference scoresRef =  getDatabaseReference().child("Note");
        scoresRef.keepSynced(true);
        getDatabaseReference().child("Note").addValueEventListener(new ValueEventListener() {
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
        FirebaseDatabase.getInstance().getReference().child("Note")
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

