package com.example.android.noteapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SingupActivity extends AppCompatActivity {
    EditText passwordEt, emailEt;
    TextView text_singTv;
    ImageView image_close;
    Button singupBt;
    FirebaseAuth mAuth;
    private static final String TAG = "SingupActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String email, password;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            Intent intent = new Intent(SingupActivity.this, home_pages.class);
            startActivity(intent);
        }
        emailEt = findViewById(R.id.emailEt);
        passwordEt = findViewById(R.id.passwordEt);
        singupBt = findViewById(R.id.singupBt);
        image_close = (ImageView) findViewById(R.id.image_close);


        image_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingupActivity.this, MainActivity.class);
                startActivity(intent);


            }
        });
        singupBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ("".equals(emailEt.getText().toString().trim())) {
                    emailEt.setError("worng email");
                    return;
                }
                if (passwordEt.getText().toString().trim().equals("")) {
                    passwordEt.setError("worng password");
                    return;
                }

                if (isNetworkAvailable()) {
                    doSignUp(emailEt.getText().toString(), passwordEt.getText().toString());
                }else{
                    Toast.makeText(SingupActivity.this, "please, check internet connection.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void doSignUp(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password).
                addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(SingupActivity.this, "Success SignUp.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SingupActivity.this, MainActivity.class);
                            startActivity(intent);


                        } else {
                            try {
                                task.getResult();
                                    Toast.makeText(SingupActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();

                                           }catch (Exception e){
                                Toast.makeText(SingupActivity.this, e.getMessage().split(":")[1], Toast.LENGTH_SHORT).show();
                            }

                        }

                    }
                });
    }
}





