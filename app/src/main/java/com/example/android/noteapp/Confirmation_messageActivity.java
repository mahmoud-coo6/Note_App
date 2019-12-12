package com.example.android.noteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Confirmation_messageActivity extends AppCompatActivity {
Button signupBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_message);
        signupBtn.findViewById(R.id.signupBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mailClient = new Intent(Intent.ACTION_VIEW);
                mailClient.setClassName("com.google.android.gm", "com.google.android.gm.ConversationListActivity");
                startActivity(mailClient);

            }
        });
    }
}
