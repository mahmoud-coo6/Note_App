package com.example.android.noteapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class NotebookTutorial2 extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notebook_tutorial_2);

        TextView skipTV = findViewById(R.id.skip);
        TextView nextTV = findViewById(R.id.next);

        skipTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotebookTutorial2.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        nextTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotebookTutorial2.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
