package com.example.buslo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    private EditText emaill, passwordd;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        emaill = (EditText) findViewById(R.id.email);
        passwordd = (EditText) findViewById(R.id.pass);
    }

    public void register(View view){
        String email = emaill.getText().toString().trim();
        String pass = passwordd.getText().toString().trim();
        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "User created", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Register.this, Home.class));
                }
                else
                    Toast.makeText(getApplicationContext(), "Signed in failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
