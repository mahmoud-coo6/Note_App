package com.example.android.noteapp;

import android.content.Intent;
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
    //    boolean saved= false;
    EditText title;
//    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_note);

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
        title = findViewById(R.id.category_title);

//        intent= getIntent();
//        if (intent != null){
//        ArrayList<Category> items= intent.getParcelableArrayListExtra(CategrayAdapter.CATEGORY_TRANSFER);
//            if (items != null) {
//                saved= true;
//                category = items.get(intent.getIntExtra(CategrayAdapter.CATEGORY_POSITION, 0));
//
//                title.setText(category.getTitle());
//                resetColor(category.getColor());
//            }
//        }

        changeColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateNoteCategory.this, SelectCategoryColor.class);
//                if (intent.hasExtra("CategoryId")){

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

//                if (category !=  null){
//                    intent.putExtra("CategoryId", category.getId());
//                }
//                intent.putExtra("color", getColor());
//                intent.putExtra("title",title.getText().toString().trim().equals("")? "My Diary": title.getText().toString());

//                    intent.putExtra()
//                }
                startActivity(intent);
            }
        });

        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if(saved){
//                    Map<String, Object> data = new HashMap<>();
//                    data.put("lastUpdate", new Date().getTime());
//                    data.put("title",intent.getStringExtra("title"));
//
//
//                    FirebaseDatabase.getInstance().getReference().child("Category").child(
//                            intent.getStringExtra("CategoryId")).updateChildren(data)
//                            .addOnFailureListener(new OnFailureListener() {
//                                @Override
//                                public void onFailure(@NonNull Exception e) {
//                                    Toast.makeText(CreateNoteCategory.this, "update category color faild", Toast.LENGTH_SHORT).show();
//
//                                }
//                            })
//                            .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                @Override
//                                public void onSuccess(Void aVoid) {
//                                    Intent intent = new Intent(CreateNoteCategory.this, home_pages.class);
//                                    startActivity(intent);
//                                    finish();
//                                }
//                            });
//                }else{
                category = new Category();
                category.setCreatedAt(new Date().getTime());
                category.setLastUpdate(new Date().getTime());
                category.setTitle((title.getText().toString().trim().equals("")) ? "My Diary" : title.getText().toString());
                category.setColor(getColor());


                String id = FirebaseDatabase.getInstance().getReference().child("Category").push().getKey();
                category.setId(id);
                FirebaseDatabase.getInstance().getReference().child("Category").child(id).setValue(category);

                Toast.makeText(CreateNoteCategory.this, "Success Create Category.", Toast.LENGTH_SHORT).show();
//                }


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
//        resetColor(color);
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
