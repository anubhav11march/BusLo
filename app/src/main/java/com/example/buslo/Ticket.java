package com.example.buslo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.view.View;

public class Ticket extends AppCompatActivity {
    TextView busnoo, routee, fare;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference mref;

    Button buyyy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
        Bundle bundle = getIntent().getExtras();
        String kms = bundle.getString("kms");
        String busno = bundle.getString("busno");
        mAuth = FirebaseAuth.getInstance();
        buyyy = (Button) findViewById(R.id.buyyyy);
        final String route = bundle.getString("route");
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Stops");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String res = "";
                for(int i=0; i<route.length(); i++){
                    String x = dataSnapshot.child(route.charAt(i)+"").getValue().toString() + "-->";
                    res+=x;
                }
                routee = findViewById(R.id.route);
                routee.setText(res);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Failed to retrieve route", Toast.LENGTH_SHORT).show();
            }
        });
        busnoo = (TextView) findViewById(R.id.busno);
        busnoo.setText("Bus No.: " + busno);
        fare = (TextView) findViewById(R.id.fare);
        fare.setText("Rs. " + Integer.parseInt(kms) * 2);
        buyyy.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                final String userId = mAuth.getCurrentUser().getUid();
                database = FirebaseDatabase.getInstance();
                mref = database.getReference().child("Users");
                mref.child(userId).child("email").setValue(mAuth.getCurrentUser().getEmail());
                mref.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String x="";
                        try {
                            x = dataSnapshot.child("noOfRides").getValue().toString();
                        }catch (Exception e){
                            mref.child(userId).child("noOfRides").setValue(1+"");
                            Toast.makeText(getApplicationContext(), "Ticket Bought Successfully", Toast.LENGTH_SHORT).show();
                        }
                        int y=0;
                        try {
                             y = Integer.parseInt(x) + 1;
                        }
                        catch (Exception e){
                            y=1;
                        }
                        mref.child(userId).child("noOfRides").setValue(y+"");
                        Toast.makeText(getApplicationContext(), "Ticket Bought Successfully", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }

//    public void buyy(View view){
//
//}

}
