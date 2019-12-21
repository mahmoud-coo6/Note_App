package com.example.android.noteapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Forgot_passwordActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    Button forgetBtn;
    EditText emailTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        forgetBtn = findViewById(R.id.recover_password);
        emailTv = findViewById(R.id.emailEt);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            Intent intent = new Intent(Forgot_passwordActivity.this, home_pages.class);
            startActivity(intent);
            finish();
        }


        forgetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ("".equals(emailTv.getText().toString().trim())) {
                    emailTv.setError("worng email");
                    return;
                }

                forgetpassword(emailTv.getText().toString());
            }

            private void forgetpassword(final String email) {

                mAuth.sendPasswordResetEmail(email)
                        .addOnSuccessListener(new OnSuccessListener() {
                            @Override
                            public void onSuccess(Object o) {
                                Toast.makeText(Forgot_passwordActivity.this, "please check your email: " + email, Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(Forgot_passwordActivity.this, ConfirmationMessage.class);
                                startActivity(intent);

                            }


                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(Forgot_passwordActivity.this, "some error happend or email not found", Toast.LENGTH_SHORT).show();

                    }


                });
            }
        });


    }
}
