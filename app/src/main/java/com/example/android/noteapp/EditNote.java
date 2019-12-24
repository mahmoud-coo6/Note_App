package com.example.android.noteapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class EditNote extends AppCompatActivity {

    Intent intent;
    Note note;
    EditText title, description;
    int color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_edit);

        title = findViewById(R.id.note_title);
        description = findViewById(R.id.note_desc);
        intent = getIntent();

        if (intent.hasExtra("new") && intent.getBooleanExtra("new", false) == true) {
            findViewById(R.id.date).setVisibility(View.GONE);

            title.setText("");
            title.setHint("Enter Note Title");
            description.setText("");
            description.setHint("Enter Note description");
            color = getResources().getColor(R.color.purple);
        } else if (intent.hasExtra("old") && intent.getBooleanExtra("old", false) == true) {
            findViewById(R.id.back).setVisibility(View.GONE);
            ArrayList<Note> notes = new ArrayList();
            notes = intent.getParcelableArrayListExtra(NotesAdapter.NOTE_TRANSFER);
            note = notes.get(intent.getIntExtra(NotesAdapter.NOTE_POSITION, 0));
            title.setText(note.getTitle());
            description.setText(note.getBody());
            CheckBox checkBox = findViewById(R.id.date);
            DateFormat formatter = new SimpleDateFormat("MMMM dd , yyyy HH:mm");
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(note.getLastUpdate());
            checkBox.setText(formatter.format(calendar.getTime()));
            LinearLayout linearLayout = findViewById(R.id.note_container);
            color = note.getColor();
            linearLayout.setBackgroundColor(color);
            title.setFocusable(false);
            description.setFocusable(false);
        }

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (intent.hasExtra("new") && intent.getBooleanExtra("new", false) == true) {
                    note = new Note();
                    note.setCreatedAt(new Date().getTime());
                    note.setLastUpdate(new Date().getTime());
                    note.setTitle((title.getText().toString().trim().equals("")) ? "My Note" : title.getText().toString());
                    note.setBody((description.getText().toString().trim().equals("")) ? "My Note Description" : description.getText().toString());
                    note.setColor(color);

                    String id = FirebaseDatabase.getInstance().getReference().child("Note").push().getKey();
                    note.setId(id);
                    note.setCategoryId(intent.getStringExtra("CategoryId"));
                    FirebaseDatabase.getInstance().getReference().child("Note").child(id).setValue(note);


                    Intent intent = new Intent(EditNote.this, CategoryNote.class);
                    intent.putExtra("CategoryId", note.getCategoryId());
                    startActivity(intent);
                    finish();
                }
            }
        });

        findViewById(R.id.change_color).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (intent.hasExtra("new") && intent.getBooleanExtra("new", false) == true) {
                    note = new Note();
                    note.setCreatedAt(new Date().getTime());
                    note.setLastUpdate(new Date().getTime());
                    note.setTitle((title.getText().toString().trim().equals("")) ? "My Note" : title.getText().toString());
                    note.setBody((description.getText().toString().trim().equals("")) ? "My Note Description" : description.getText().toString());
                    note.setColor(color);

                    String id = FirebaseDatabase.getInstance().getReference().child("Note").push().getKey();
                    note.setId(id);
                    note.setCategoryId(intent.getStringExtra("CategoryId"));
                    FirebaseDatabase.getInstance().getReference().child("Note").child(id).setValue(note);


                    Intent intent = new Intent(EditNote.this, NoteSelectColor.class);

                    intent.putExtra("color", color);
                    intent.putExtra("title", note.getTitle());
                    intent.putExtra("description", note.getBody());
                    intent.putExtra("date", note.getLastUpdate());
                    intent.putExtra("NoteId", note.getId());
                    intent.putExtra("CategoryId", note.getCategoryId());
                    startActivity(intent);
                    finish();
                } else if (intent.hasExtra("old") && intent.getBooleanExtra("old", false) == true) {
                    ArrayList<Note> notes = new ArrayList();
                    notes = intent.getParcelableArrayListExtra(NotesAdapter.NOTE_TRANSFER);
                    note = notes.get(intent.getIntExtra(NotesAdapter.NOTE_POSITION, 0));


//                    Intent intent = new Intent(EditNote.this, NoteSelectColor.class);

                    intent.putExtra("color", note.getColor());
                    intent.putExtra("title", note.getTitle());
                    intent.putExtra("description", note.getBody());
                    intent.putExtra("date", note.getLastUpdate());
                    intent.putExtra("NoteId", note.getId());
                    intent.putExtra("CategoryId", note.getCategoryId());
                    startActivity(intent);
                    finish();
                }
            }
        });

    }

}
