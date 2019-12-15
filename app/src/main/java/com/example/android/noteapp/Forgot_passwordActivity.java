package com.example.android.noteapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Forgot_passwordActivity extends AppCompatActivity {
    //Button forgetBtn=findViewById(R.id.forgetBtn);
//EditText emailTv = findViewById(R.id.emailTv);
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
//        if (user!=null)
//        {
//            Intent intent = new Intent(LoginActivity.this ,home_pages.class);
//            startActivity(intent);
//        }

//        if(" ".equals(emailTv.getText().toString())) {
//            emailTv.setError("worng email");
//            return;
//        }
//        forgetBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                forgetpassword(emailTv.getText().toString());
//            }
//
//            private void forgetpassword(String email) {
//
//                mAuth.sendPasswordResetEmail(email)
//                        .addOnSuccessListener(new OnSuccessListener() {
//                            @Override
//                            public void onSuccess(Object o) {
//                                Toast.makeText(Forgot_passwordActivity.this, "please go tom mail w", Toast.LENGTH_SHORT).show();
//
//                                Intent intent = new Intent(Forgot_passwordActivity.this, Confirmation_messageActivity.class);
//                                startActivity(intent);
//
//                            }
//
//
//                        }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//
//                        Toast.makeText(Forgot_passwordActivity.this, "some error happend or email not found", Toast.LENGTH_SHORT).show();
//
//                    }
//
//
//            });            }
//        });
//


    }
}
