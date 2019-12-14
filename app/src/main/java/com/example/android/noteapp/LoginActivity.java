package com.example.android.noteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class LoginActivity extends AppCompatActivity {
    TextView text_singinTv,forgetTv;
            EditText email_logEt,password_logEt;
            ImageView image_close_login;
    Button loginButon;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        String email ,password;
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user!=null)
        {
            Intent intent = new Intent(LoginActivity.this ,home_pages.class);
            startActivity(intent);
        }

//        email_logEt = findViewById(R.id.email_logEt);
//        password_logEt = findViewById(R.id.password_logEt);
//        loginButon = findViewById(R.id.loginButon);
//        image_close_login = (ImageView)findViewById(R.id.image_close_login);
//        if(" ".equals(email_logEt.getText().toString())) {
//            email_logEt.setError("worng email");
//            return;
//        }
//        if(password_logEt.getText().toString().equals(" ")) {
//            password_logEt.setError("worng password");
//            return ;
//        }
//        findViewById(R.id.text_singinTv).setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(LoginActivity.this, home_pages.class);
//                startActivity(intent);
//
//            }
//        });
//        findViewById(R.id.forgetTv).setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(LoginActivity.this, Forgot_passwordActivity.class);
//                startActivity(intent);
//
//            }
//        });



        image_close_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);


            }
        });
        loginButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSignIn(email_logEt.getText().toString(), password_logEt.getText().toString());
            }
        });
    }

    private void doSignIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).
                addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);


                        } else {
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }});
    }
}





