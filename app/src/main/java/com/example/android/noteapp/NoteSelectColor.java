package com.example.android.noteapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.devs.vectorchildfinder.VectorChildFinder;
import com.devs.vectorchildfinder.VectorDrawableCompat;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class NoteSelectColor extends AppCompatActivity {

    LinearLayout container;
    int color;
    Intent intent;
    String categoryId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_select_color);

        container = findViewById(R.id.select_color_container);
        final TextView title = findViewById(R.id.title);
        final TextView description = findViewById(R.id.description);
        final CheckBox date = findViewById(R.id.date);

        intent = getIntent();
        categoryId = intent.getStringExtra("CategoryId");
        title.setText(intent.getStringExtra("title"));
        description.setText(intent.getStringExtra("description"));
        color = intent.getIntExtra("color", 0);
        DateFormat formatter = new SimpleDateFormat("MMMM dd , yyyy HH:mm");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(intent.getLongExtra("date", 0));
        date.setText(formatter.format(calendar.getTime()));
        container.setBackgroundColor(color);

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> data = new HashMap<>();
                data.put("lastUpdate", new Date().getTime());
                data.put("color", color);


                FirebaseDatabase.getInstance().getReference().child("Note").child(
                        intent.getStringExtra("NoteId")).updateChildren(data)
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(NoteSelectColor.this, "update category color faild", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Intent intent = new Intent(NoteSelectColor.this, CategoryNote.class);
                                intent.putExtra("CategoryId", categoryId);
                                startActivity(intent);
                                finish();
                            }
                        });
            }
        });
    }


    private void resetColor(int color) {
        container.setBackgroundColor(color);
    }


    public void selectColor(View view) {
        VectorChildFinder vector = new VectorChildFinder(this, view.getResources().getIdentifier(view.getTag().toString(), "drawable", getPackageName()), (ImageView) view);
        VectorDrawableCompat.VFullPath path1 = vector.findPathByName("path1");
        color = path1.getFillColor();
        resetColor(color);
    }

}
