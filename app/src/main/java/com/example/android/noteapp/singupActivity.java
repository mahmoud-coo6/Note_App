package com.example.android.noteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.example.android.noteapp.R.id.image_close;

public class singupActivity extends AppCompatActivity {
EditText passwordEt,emailEt;
    TextView text_singTv;
    ImageView image_close;
    Button singupBt;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        String email ,password;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        if (user!=null)
        {
            Intent intent = new Intent(singupActivity.this ,home_pages.class);
            startActivity(intent);
        }
        emailEt = findViewById(R.id.emailEt);
        passwordEt = findViewById(R.id.passwordEt);
        singupBt = findViewById(R.id.singupBt);
         image_close = (ImageView)findViewById(R.id.image_close);
        if(" ".equals(emailEt.getText().toString())) {
           emailEt.setError("worng email");
           return;
        }
        if(passwordEt.getText().toString().equals(" ")) {
            passwordEt.setError("worng password");
            return ;
        }
findViewById(R.id.text_singTv).setOnClickListener(new View.OnClickListener() {

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(singupActivity.this, LoginActivity.class);
        startActivity(intent);

    }
});



image_close.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(singupActivity.this, MainActivity.class);
        startActivity(intent);


    }
});
        singupBt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    doSignUp(emailEt.getText().toString(), passwordEt.getText().toString());
                }
            });
}

    private void doSignUp(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).
                addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    Intent intent = new Intent(singupActivity.this, MainActivity.class);
                    startActivity(intent);


                } else {
                    Toast.makeText(singupActivity.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                }

            }});
                }
    }





