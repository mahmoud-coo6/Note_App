package com.example.android.noteapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Notebook_tutorial_1 extends AppCompatActivity {
    SharedPreferences prefs = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notebook_tutorial_1);
        // Perhaps set content view here

        prefs = getSharedPreferences("com.example.android.noteapp", MODE_PRIVATE);

        TextView skipTV = findViewById(R.id.skip);
        TextView nextTV = findViewById(R.id.next);

        skipTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Notebook_tutorial_1.this, MainActivity.class);
                startActivity(intent);
            }
        });

        nextTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Notebook_tutorial_1.this, Notebook_tutorial_2.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();


        if (prefs.getBoolean("firstrun", true)) {
            prefs.edit().putBoolean("firstrun", false).apply();
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}

