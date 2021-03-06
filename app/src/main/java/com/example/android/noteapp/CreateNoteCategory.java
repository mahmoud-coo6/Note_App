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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.List;

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
        title = findViewById(R.id.category_title);
        title.setPaintFlags(title.getPaintFlags() & (~Paint.UNDERLINE_TEXT_FLAG));


        findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateNoteCategory.this, home_pages.class);

                startActivity(intent);
                finish();
            }
        });
        currentUser = MyFirebaseController.getCurrentUserId();

        changeColor = findViewById(R.id.change_color);
        book = findViewById(R.id.book);

        Intent intent = getIntent();
        currentUserId = intent.getStringExtra("userId");

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
                category.setUserId(currentUser.getUid());
                MyFirebaseController.getDatabaseReference().child("Category").child(id).setValue(category);

//                final Map<String, Object> data = new HashMap<>();
//                categoryId= new ArrayList<>();
//                initData();
//                categoryId.add(category.getId());
//                data.put("categoryId", categoryId);
//                Log.i("test", "onClick: 4" +currentUser.getUid()+" : "+currentUser.getEmail()+ " : "+categoryId);
//
//               MyFirebaseController.getDatabaseReference().child("User").child(currentUser.getUid()).updateChildren(data)
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Toast.makeText(CreateNoteCategory.this, "update category color faild", Toast.LENGTH_SHORT).show();
//
//                            }
//                        })
//                        .addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void aVoid) {
//
//                            }
//                        });

                intent.putExtra("color", getColor());
                intent.putExtra("title", category.getTitle());
                intent.putExtra("CategoryId", category.getId());
                intent.putExtra("userId", category.getUserId());

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
                category.setUserId(currentUser.getUid());
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
//    private void initData() {
////        Log.i("test","step 1 "+ currentUser.getUid()+" : "+currentUserId);
//
//        FirebaseDatabase.getInstance().getReference().child("User").orderByChild("uid").equalTo(currentUserId).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
////                Log.i("test","step 2" +dataSnapshot.toString()+" count "+dataSnapshot.getChildrenCount());
////                categoryId.clear();
//
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//
//                    User user = snapshot.getValue(User.class);
//                    Log.i("test","step 3");
//                    Log.i("test", "onDataChange: "+user.getCategoryId());
////                    categoryId.add(user.getCategoryId().toString());
//                    Log.i("test","step 4");
//                    for (String id: user.getCategoryId()){
//                        categoryId.add(id);
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }

}
