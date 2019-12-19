package com.example.android.noteapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class NoteEditActivity extends AppCompatActivity {
 Button saveBtn;
    EditText category_nameTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);
        findViewById(R.id.saveBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category_nameTv = findViewById(R.id.textView);
                String name_catage= category_nameTv.getText().toString();
                FirebaseAuth mAuth;
                mAuth = FirebaseAuth.getInstance();
                //catogory  information
                Categraty categraty=new Categraty(name_catage, 2,Color.blue2,null);
                ArrayList<Categraty> categraties=new ArrayList<Categraty>();
                categraties.add(categraty);
                //id auto genurate
                User user=new User(1,mAuth.getCurrentUser().getEmail(),categraties);

                FirebaseDatabase.getInstance().getReference("Users").
                        child(mAuth.getCurrentUser().getUid()).setValue(user)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                  @Override
                                                  public void onSuccess(Void aVoid) {
                                                      Toast.makeText(NoteEditActivity.this, "the catogory is done add", Toast.LENGTH_SHORT).show();
                                                  }

                                              });





                                }
                        });




            }
        }

