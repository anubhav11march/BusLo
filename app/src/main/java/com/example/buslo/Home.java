package com.example.buslo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Home extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private EditText a, b, c, d, e, f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference().child("Bus");
        a = findViewById(R.id.aa);
        b = findViewById(R.id.aa1);
        c = findViewById(R.id.aa2);
        d = findViewById(R.id.aa3);
        e = findViewById(R.id.aa4);
        f = findViewById(R.id.aa5);


        startActivity(new Intent(Home.this, Main.class));

    }
    private int y = 113;
    public void done(View view){
        String x = ""+y;
        String aa = a.getText().toString().trim();
        String aa1 = b.getText().toString().trim();
        String aa2 = c.getText().toString().trim();
        String aa3 = d.getText().toString().trim();
        String aa4 = e.getText().toString().trim();
        String aa5 = f.getText().toString().trim();
        mRef.child(x).child("avgtime").setValue(aa);
        mRef.child(x).child("cap").setValue(aa1);
        mRef.child(x).child("lat").setValue(aa2);
        mRef.child(x).child("lon").setValue(aa2);
        mRef.child(x).child("lstart").setValue(aa3);
        mRef.child(x).child("occ").setValue(aa4);
        mRef.child(x).child("routedistance").setValue(aa5);
        mRef.child(x).child("stops").child("1").setValue("1");
        mRef.child(x).child("stops").child("2").setValue("2");
        mRef.child(x).child("stops").child("3").setValue("3");
        mRef.child(x).child("stops").child("4").setValue("4");
        mRef.child(x).child("stops").child("5").setValue("5");
        y++;
    }
}
