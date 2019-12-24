package com.example.android.noteapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SelectCategoryColor extends AppCompatActivity {

    ImageView category;
    int color;
    Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_color_page);

        findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectCategoryColor.this, CreateNoteCategory.class);
                startActivity(intent);
                finish();
            }
        });


        category = findViewById(R.id.category_img);
        final TextView title = findViewById(R.id.title);


        intent = getIntent();
        resetColor(intent.getIntExtra("color", 0));
        title.setText(intent.getStringExtra("title"));

        color = getColor();
        findViewById(R.id.saveBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> data = new HashMap<>();
                data.put("lastUpdate", new Date().getTime());
                data.put("color", color);


                MyFirebaseController.getDatabaseReference().child("Category").child(
                        intent.getStringExtra("CategoryId")).updateChildren(data)
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(SelectCategoryColor.this, "update category color faild", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Intent intent = new Intent(SelectCategoryColor.this, home_pages.class);
                                startActivity(intent);
                                finish();
                            }
                        });
            }
        });

    }

    private void resetColor(int color) {
        VectorChildFinder vector = new VectorChildFinder(this, R.drawable.ic_links_notebook, category);
        VectorDrawableCompat.VFullPath path1 = vector.findPathByName("path1");
        path1.setFillColor(color);
    }

    public void selectColor(View view) {
        VectorChildFinder vector = new VectorChildFinder(this, view.getResources().getIdentifier(view.getTag().toString(), "drawable", getPackageName()), (ImageView) view);
        VectorDrawableCompat.VFullPath path1 = vector.findPathByName("path1");
        color = path1.getFillColor();
        resetColor(color);
    }

    public int getColor() {
        VectorChildFinder vector = new VectorChildFinder(SelectCategoryColor.this, R.drawable.ic_links_notebook, category);
        VectorDrawableCompat.VFullPath path1 = vector.findPathByName("path1");
        color = path1.getFillColor();
        return color;
    }

}
