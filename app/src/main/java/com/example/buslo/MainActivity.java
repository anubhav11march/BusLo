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

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText emaill, passwordd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        emaill = (EditText) findViewById(R.id.email);
        passwordd = (EditText) findViewById(R.id.pass);
//        mAuth.signOut();
    if(mAuth.getCurrentUser()!=null) {
        startActivity(new Intent(MainActivity.this, Main.class));
        Toast.makeText(this, "Logged in as: " + mAuth.getCurrentUser().getEmail(), Toast.LENGTH_SHORT).show();
    }
    }

    public void signin(View view){
        String email = emaill.getText().toString().trim();
        String password = passwordd.getText().toString().trim();
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Signed in as: " + mAuth.getCurrentUser().getEmail(), Toast.LENGTH_SHORT).show();
                    finish();}
                else
                    Toast.makeText(getApplicationContext(), "Signed in failed", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, Home.class));
            }
        });
    }

    public void register(View view){
        startActivity(new Intent(MainActivity.this, Register.class));
    }
}
