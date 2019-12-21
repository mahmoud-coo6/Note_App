package com.example.android.noteapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class NoteList extends AppCompatActivity {
    Button saveBtn;
    EditText category_nameTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_list);
        findViewById(R.id.saveBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                category_nameTv = findViewById(R.id.textView);
                String name_catage = category_nameTv.getText().toString();
                FirebaseAuth mAuth;
                mAuth = FirebaseAuth.getInstance();
                //catogory  information
//                 public Category(String id, String title, String body, Color color, long createdAt) {

//                Category categraty=new Category(name_catage, 2,Color.blue2,null);
//                ArrayList<Category> categraties=new ArrayList<Category>();
//                categraties.add(categraty);

                //id auto genurate
//                User user=new User(1,mAuth.getCurrentUser().getEmail(),categraties);

//                FirebaseDatabase.getInstance().getReference("Users").
//                        child(mAuth.getCurrentUser().getUid()).setValue(user)
//                        .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                                  @Override
//                                                  public void onSuccess(Void aVoid) {
//                                                      Toast.makeText(NoteList.this, "the catogory is done add", Toast.LENGTH_SHORT).show();
//                                                  }
//
//                                              });

            }
        });


    }


}

