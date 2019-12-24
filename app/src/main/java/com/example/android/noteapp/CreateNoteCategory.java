package com.example.android.noteapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.devs.vectorchildfinder.VectorChildFinder;
import com.devs.vectorchildfinder.VectorDrawableCompat;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.android.noteapp.MyFirebaseController.getDatabaseReference;

public class CreateNoteCategory extends AppCompatActivity {

    Category category;
    ImageView changeColor, book;
    EditText title;
    List<String> categoryId;
    FirebaseUser currentUser;
    String currentUserId;

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
        currentUser= MyFirebaseController.getCurrentUserId();

        changeColor = findViewById(R.id.change_color);
        book = findViewById(R.id.book);
        title = findViewById(R.id.category_title);

        Intent intent= getIntent();
        currentUserId= intent.getStringExtra("userId");

        changeColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateNoteCategory.this, SelectCategoryColor.class);

                category = new Category();
                category.setCreatedAt(new Date().getTime());
                category.setLastUpdate(new Date().getTime());
                category.setTitle((title.getText().toString().trim().equals("")) ? "My Diary" : title.getText().toString());
                category.setColor(getColor());

                String id = MyFirebaseController.getDatabaseReference().child("Category").push().getKey();
                category.setId(id);
                MyFirebaseController.getDatabaseReference().child("Category").child(id).setValue(category);

                final Map<String, Object> data = new HashMap<>();
                categoryId= new ArrayList<>();
                initData();
                categoryId.add(category.getId());
                data.put("categoryId", categoryId);
                Log.i("test", "onClick: 4" +currentUser.getUid()+" : "+currentUser.getEmail()+ " : "+categoryId);

               MyFirebaseController.getDatabaseReference().child("User").child(currentUser.getUid()).updateChildren(data)
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(CreateNoteCategory.this, "update category color faild", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                            }
                        });

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
    private void initData() {
        Log.i("test","step 1 "+ currentUser.getUid()+" : "+currentUserId);

        getDatabaseReference().child("User").orderByChild("uid").equalTo(currentUserId).orderByChild("categoryId").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.i("test","step 2" +dataSnapshot.toString());

//                categoryId.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    User user = snapshot.getValue(User.class);
                    Log.i("test","step 3");
                    Log.i("test", "onDataChange: "+user.getCategoryId().toString());
//                    categoryId.add(user.getCategoryId().toString());
                    Log.i("test","step 4");
                    for (String id: user.getCategoryId()){
                        categoryId.add(id);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
