package com.example.buslo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class Bus_Stops extends AppCompatActivity {
    private DatabaseReference mref;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("Stops");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus__stops);
        database = FirebaseDatabase.getInstance();
        mref = database.getReference().child("Stops");
//        recyclerView = (RecyclerView) findViewById(R.id.rv);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
//        FirebaseRecyclerOptions<BusStopp> options = new FirebaseRecyclerOptions.Builder<BusStopp>()
//                .setQuery(query, BusStopp.class)
//                .build();
//
//        FirebaseRecyclerAdapter FBRA = new FirebaseRecyclerAdapter<BusStopp, BusStoppViewHolder>(options) {
//
//            @NonNull
//            @Override
//            public BusStoppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stop, parent, false);
//                return new BusStoppViewHolder(view);
//            }
//
//            @Override
//            protected void onBindViewHolder(@NonNull BusStoppViewHolder holder, int position, @NonNull BusStopp model) {
//                final String stop = getRef(position).;
//                holder.setName(stop);
//            }
//        };
//        FBRA.startListening();
//        recyclerView.setAdapter(FBRA);
//    }
//
//    public class BusStoppViewHolder extends RecyclerView.ViewHolder{
//        View mView;
//        public BusStoppViewHolder(@NonNull View itemView){
//            super(itemView);
//            mView = itemView;
//        }
//
//        public void setName(String name){
//            TextView stop = (TextView) mView.findViewById(R.id.from);
//            stop.setText(name);
//        }
//
//    }

}
