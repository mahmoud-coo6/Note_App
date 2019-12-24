package com.example.android.noteapp;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.devs.vectorchildfinder.VectorChildFinder;
import com.devs.vectorchildfinder.VectorDrawableCompat;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class CreateNoteCategory extends AppCompatActivity {

    Category category;
    ImageView changeColor, book;
    EditText title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_note);
        title = findViewById(R.id.category_title);
        title.setPaintFlags(title.getPaintFlags() & (~ Paint.UNDERLINE_TEXT_FLAG));



        findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateNoteCategory.this, home_pages.class);
                startActivity(intent);
                finish();
            }
        });

        changeColor = findViewById(R.id.change_color);
        book = findViewById(R.id.book);

        changeColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateNoteCategory.this, SelectCategoryColor.class);

                category = new Category();
                category.setCreatedAt(new Date().getTime());
                category.setLastUpdate(new Date().getTime());
                category.setTitle((title.getText().toString().trim().equals("")) ? "My Diary" : title.getText().toString());
                category.setColor(getColor());

                String id = FirebaseDatabase.getInstance().getReference().child("Category").push().getKey();
                category.setId(id);
                FirebaseDatabase.getInstance().getReference().child("Category").child(id).setValue(category);

                intent.putExtra("color", getColor());
                intent.putExtra("title", category.getTitle());
                intent.putExtra("CategoryId", category.getId());

                startActivity(intent);
            }
        });

        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                category = new Category();
                category.setCreatedAt(new Date().getTime());
                category.setLastUpdate(new Date().getTime());
                category.setTitle((title.getText().toString().trim().equals("")) ? "My Diary" : title.getText().toString());
                category.setColor(getColor());


                String id = FirebaseDatabase.getInstance().getReference().child("Category").push().getKey();
                category.setId(id);
                FirebaseDatabase.getInstance().getReference().child("Category").child(id).setValue(category);
                Toast.makeText(CreateNoteCategory.this, "Success Create Category.", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(CreateNoteCategory.this, home_pages.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public int getColor() {
        VectorChildFinder vector = new VectorChildFinder(CreateNoteCategory.this, R.drawable.ic_links_notebook, book);
        VectorDrawableCompat.VFullPath path1 = vector.findPathByName("path1");
        int color = path1.getFillColor();
        return color;
    }

    private void resetColor(int color) {
        VectorChildFinder vector = new VectorChildFinder(this, R.drawable.ic_picker2, changeColor);
        VectorDrawableCompat.VFullPath path1 = vector.findPathByName("path1");
        path1.setFillColor(color);

        vector = new VectorChildFinder(this, R.drawable.ic_links_notebook, book);
        path1 = vector.findPathByName("path1");
        path1.setFillColor(color);
    }
}
